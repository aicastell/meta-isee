# This file defines function used for unpacking the .bin file downloaded over
# the http.
#  BINFILE - name of the install jammer .bin file
#  TARFILE - name of the tar file inside the install jammer
#  TI_BIN_UNPK_ARGS   - contains the arguments to be passed to the bin file.
#  TI_BIN_UNPK_CMDS   - contains list of commands separated with colon to be 
#                       passed while unpacking the bin file. The keyword 
#                       workdir expands to WORKDIR and commands are appendded
#                       with '\n'. Eg. TI_BIN_UNPK_CMDS="Y:Y: qY:workdir"
#  TI_BIN_UNPK_WDEXT  - This variable extends workdir path, if user wants to put
#                       the output in some internal directory

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('ti_bin_do_unpack', d)
}

TI_BIN_UNPK_WDEXT ?= ""
python ti_bin_do_unpack() {

    import os

    # InstallJammer requires 32bit version of glibc
    lib32path = '/lib'
    if os.path.exists('/lib64') and (os.path.islink('/lib64') or os.path.islink('/lib') or os.path.exists('/lib32')):
        lib32path = '/lib32'
    if not os.path.exists('%s/libc.so.6' % lib32path):
        bb.warn("TI installer requires 32bit glibc libraries for proper operation\nrun 'yum install glibc.i686' on Fedora or 'apt-get install ia32-libs' on Ubuntu/Debian")

    localdata = bb.data.createCopy(d)
    bb.data.update_data(localdata)

    binfile  = d.getVar('BINFILE', localdata)
    binfile  = bb.data.expand(binfile, localdata)

    # Change to the working directory
    save_cwd = os.getcwd()
    workdir  = d.getVar('WORKDIR', localdata)
    workdir  = bb.data.expand(workdir, localdata)
    os.chdir(workdir)

    # Get unpack args
    arg_string = d.getVar('TI_BIN_UNPK_ARGS', localdata)
    arg_string = bb.data.expand(arg_string, localdata)

    # Get unpack commands
    cmd_string = d.getVar('TI_BIN_UNPK_CMDS', localdata)
    cmd_list = cmd_string.split( ":" )

    # Make the InstallJammer binary executable so we can run it
    os.chmod(binfile, 0o755)

    # Run the InstallJammer binary and accept the EULA
    filename = "HOME=%s ./%s %s" % (workdir, binfile, arg_string)
    
    # Test executable by printing installer version or help screen (--version currently broken for some installers) - this is currently broken in some IJ installers - 
    #comment out for now if os.system(filename + " --version") != 0:
    #   print("ERROR: ti-eula-unpack: failed to execute binary installer")
    #   raise bb.build.FuncFailed()
 
    f = os.popen(filename,'w')
    for cmd in cmd_list:
        if cmd == "workdir":
            wdext = d.getVar('TI_BIN_UNPK_WDEXT', localdata)
            wdext = bb.data.expand(wdext, localdata)
            cmd = workdir+wdext
        f.write(cmd+'\n');
    f.close()

    # Expand the tarball that was created if required
    tarfile  = d.getVar('TARFILE', localdata)    
    if bool(tarfile) == True:
        tarfile  = bb.data.expand(tarfile, localdata)
        tcmd = 'tar x --no-same-owner -f %s -C %s' % (tarfile, workdir)
        if os.system(tcmd) != 0:
            print("ERROR: ti-eula-unpack: failed to extract tarfile")
            raise bb.build.FuncFailed()

    # Return to the previous directory
    os.chdir(save_cwd)
}
