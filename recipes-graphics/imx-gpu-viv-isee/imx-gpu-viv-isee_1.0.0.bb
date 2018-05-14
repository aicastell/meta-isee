SUMMARY = "Userspace GPU driver for i.MX6"
DESCRIPTION = "Userspace GPU driver and apps for i.MX6"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PE = "1"

EXTRA_PROVIDES = ""
EXTRA_PROVIDES_append_imxgpu3d = " virtual/libgl virtual/libgles1 virtual/libgles2"
EXTRA_PROVIDES_append_mx6q     = " virtual/opencl-icd opencl-headers"
PROVIDES += "imx-gpu-viv-isee virtual/wayland-egl virtual/libgal-x11 virtual/libgal-fb virtual/egl virtual/libopenvg ${EXTRA_PROVIDES}"

DEPENDS += "libdrm libffi"
#DEPENDS += "libdrm libffi virtual/libx11 libxdamage libxext libxfixes libglapi"

RPROVIDES_${PN}_imxgpu3d += "imx-gpu-viv-isee"

inherit fsl-eula-unpack

SRC_URI = "${FSL_MIRROR}/imx-gpu-viv-6.2.2.p0-aarch32.bin;fsl-eula=true"
SRC_URI[md5sum] = "7d43f73b8bc0c1c442587f819218a1d5"
SRC_URI[sha256sum] = "4f93a4412c93ca5959aa2437bfed2ecbaf983b5b272be5977f76a967de5db150"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

# Note : If you add a package here, to prevent a naming conflict see the python_anonymous() futher below
PACKAGES =+ " \
    libclc-imx libclc-imx-dev \
    libgl-imx libgl-imx-dev \
    libgles-imx libgles-imx-dev \
    libgles2-imx libgles2-imx-dev \
    libgles3-imx-dev \
    libglslc-imx libglslc-imx-dev \
    libopencl-imx libopencl-imx-dev \
    libopenvg-imx libopenvg-imx-dev \
    libvdk-imx libvdk-imx-dev \
    libegl-imx libegl-imx-dev \
    libgal-imx libgal-imx-dev \
    libvsc-imx \
    libllvm-imx \
    libvivante-dri-imx \
    libgbm-imx libgbm-imx-dev \
    libwayland-viv-imx libwayland-viv-imx-dev \
    libgc-wayland-protocol-imx libgc-wayland-protocol-imx-dev \
    libwayland-egl-imx-dev \
    imx-gpu-viv-tools \
    imx-gpu-viv-demos \
    libvulkan-imx libvulkan-imx-dev \
    libopenvx-imx libopenvx-imx-dev \
"

# Inhibit warnings about files being stripped.
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# For the packages that make up the OpenGL interfaces, inject variables so that
# they don't get Debian-renamed (which would remove the -imx suffix).
#
# FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
# the source we cannot fix it. Disable the insane check for now.
python __anonymous() {
    packages = d.getVar('PACKAGES', True).split()
    for p in packages:
        d.appendVar("INSANE_SKIP_%s" % p, " ldflags")

    for p in (("libegl", "libegl1"), ("libgl", "libgl1"),
              ("libgles1", "libglesv1-cm1"), ("libgles2", "libglesv2-2"),
              ("libgles3",) , ("libvulkan",)):
        fullp = p[0] + "-imx"
        pkgs = " ".join(p)
        d.setVar("DEBIAN_NOAUTONAME_" + fullp, "1")
        d.appendVar("RREPLACES_" + fullp, pkgs)
        d.appendVar("RPROVIDES_" + fullp, pkgs)
        d.appendVar("RCONFLICTS_" + fullp, pkgs)

        # For -dev, the first element is both the Debian and original name
        fullp += "-dev"
        pkgs = p[0] + "-dev"
        d.setVar("DEBIAN_NOAUTONAME_" + fullp, "1")
        d.appendVar("RREPLACES_" + fullp, pkgs)
        d.appendVar("RPROVIDES_" + fullp, pkgs)
        d.appendVar("RCONFLICTS_" + fullp, pkgs)
}

# Hack to add all libraries of the gpu_sdk regardless of the imx-gpu-viv of freescale

do_install () {
    # Directories
    install -d ${D}${libdir}
    install -d ${D}${libdir}/pkgconfig
    install -d ${D}${libdir}/dri
    install -d ${D}${bindir}
    install -d ${D}${includedir}
    install -d ${D}/etc
    install -d ${D}/etc/OpenCL/vendors
    install -d ${D}/opt

    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-core/usr/lib/* ${D}${libdir}/
    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-core/usr/include/* ${D}${includedir}/
    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-core/etc/* ${D}/etc/
    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-core/etc/* ${D}/etc/OpenCL/vendors/
    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-tools/gmem-info/usr/bin/* ${D}${bindir}/
    cp -R ${WORKDIR}/imx-gpu-viv-6.2.2.p0-aarch32/gpu-demos/opt/* ${D}/opt/

    rm -Rf  ${D}${libdir}/pkgconfig/egl.pc
    rm -Rf  ${D}${libdir}/libGAL.so
    rm -Rf  ${D}${libdir}/libVDK.so
    cp -Rf ${D}${libdir}/pkgconfig/egl_linuxfb.pc ${D}${libdir}/pkgconfig/egl.pc
    cp -Rf ${D}${libdir}/libGAL-fb.so ${D}${libdir}/libGAL.so
    cp -Rf ${D}${libdir}/libVDK-fb.so ${D}${libdir}/libVDK.so

}

INSANE_SKIP_${PN} += "dev-elf dev-deps"
INSANE_SKIP_${PN}-dev += "dev-elf dev-deps"

# Package libclc-imx
INSANE_SKIP_libclc-imx += "dev-deps"
FILES_libclc-imx = "${libdir}/libCLC.so"
FILES_libclc-imx-dev = "${libdir}/libCLC.so"
RDEPENDS_libclc-imx = "libvsc-imx"


# Package libgal-imx
INSANE_SKIP_libgal-imx += "dev-so"
FILES_libgal-imx = "${libdir}/libGAL*"
FILES_libgal-imx-dev = "${libdir}/libGAL* ${includedir}/HAL"
RPROVIDES_libgal-imx += "libgal-imx"
INSANE_SKIP_libgal-imx += "build-deps"


# Package libgbm-imx
INSANE_SKIP_libgbm-imx += "dev-so"
FILES_libgbm-imx = "${libdir}/libgbm* ${libdir}/gbm_viv*"
FILES_libgbm-imx-dev = "${libdir}/pkgconfig/gbm.pc ${includedir}/gbm.h ${libdir}/libgbm* ${libdir}/gbm_viv*"
RDEPENDS_libgbm-imx += "libdrm"


# Package libegl-imx
INSANE_SKIP_libegl-imx += "dev-so"
FILES_libegl-imx = "${libdir}/libEGL*"
FILES_libegl-imx-dev = "${includedir}/EGL ${includedir}/KHR ${libdir}/pkgconfig/egl*"
RDEPENDS_libegl-imx += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgc-wayland-protocol-imx libwayland-viv-imx libgc-wayland-protocol-imx', '', d)}"
RDEPENDS_libegl-imx-dev += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libwayland-egl-imx-dev', '', d)}"
RDEPENDS_libegl-imx += "libgal-imx"


# Package libgles-imx
INSANE_SKIP_libgles-imx += "dev-so"
FILES_libgles-imx = "${libdir}/libGLESv1* ${libdir}/libGLES_*"
FILES_libgles-imx-dev = "${includedir}/GLES ${libdir}/libGLESv1* ${libdir}/libGLES_* ${libdir}/pkgconfig/glesv1*"


# Package libgles2-imx
INSANE_SKIP_libgles2-imx += "dev-so"
FILES_libgles2-imx = "${libdir}/libGLESv2*"
FILES_libgles2-imx-dev = "${includedir}/GLES2 ${libdir}/libGLESv2* ${libdir}/pkgconfig/glesv2*"
RDEPENDS_libgles2-imx += "libgal-imx libvsc-imx libopenvg-imx libglslc-imx"


# Package libgles3-imx
FILES_libgles3-imx-dev = "${includedir}/GLES3"

# Package libglslc-imx
FILES_libglslc-imx = "${libdir}/libGLSLC*"
FILES_libglslc-imx-dev = "${libdir}/libGLSLC*"


# Package libgl-imx
INSANE_SKIP_libgl-imx += "dev-so"
FILES_libgl-imx = "${libdir}/libGL.* ${libdir}libGL-* ${includedir}/GL"
FILES_libgl-imx-dev = "${libdir}/libGL.* ${libdir}/libGL-* ${includedir}/GL ${libdir}/pkgconfig/gl*"
# Includes GL headers from mesa
RDEPENDS_libgl-imx-dev += "libgl-mesa-dev"


# Package libllvm-imx
INSANE_SKIP_libllvm-imx += "dev-so"
FILES_libllvm-imx = "${libdir}/libLLVM*"
FILES_libllvm-imx-dev = "${libdir}/libLLVM*"


# Package libopencl-imx
INSANE_SKIP_libopencl-imx += "dev-so"
FILES_libopencl-imx = "${libdir}/libOpenCL* ${libdir}/libVivanteOpenCL* ${sysconfdir}/OpenCL/vendors/Vivante.icd"
FILES_libopencl-imx-dev = "${includedir}/CL ${libdir}/libOpenCL* ${libdir}/libVivanteOpenCL* ${sysconfdir}/OpenCL/vendors/Vivante.icd"
RDEPENDS_libopencl-imx= "libclc-imx"


# Package libopenvg-imx
INSANE_SKIP_libopenvg-imx += "dev-so"
FILES_libopenvg-imx = "${libdir}/libOpenVG*"
FILES_libopenvg-imx-dev = "${includedir}/VG ${libdir}/libOpenVG* ${libdir}/pkgconfig/vg*"


# Package libvdk-imx
INSANE_SKIP_libvdk-imx += "dev-so"
FILES_libvdk-imx = "${libdir}/libVDK*"
FILES_libvdk-imx-dev = "${includedir}/*vdk*.h ${libdir}/libVDK*"


# Package libvsc-imx
INSANE_SKIP_libvsc-imx += "dev-so"
FILES_libvsc-imx = "${libdir}/libVSC*"
FILES_libvsc-imx-dev = "${libdir}/libVSC*"


# Package libwayland-viv-imx
INSANE_SKIP_libwayland-viv-imx += "dev-so staticdev"
FILES_libwayland-viv-imx =  "${libdir}/libwayland-viv*"
FILES_libwayland-viv-imx-dev = "${includedir}/wayland-viv ${libdir})/libwayland-viv* ${libdir}/pkgconfig/wayland-viv.pc"

# Package libwayland-egl-imx-dev
FILES_libwayland-egl-imx-dev = "${libdir}/pkgconfig/wayland-egl.pc"


# Package libgc-wayland-protocol-imx
INSANE_SKIP_libgc-wayland-protocol-imx += "dev-so staticdev"
FILES_libgc-wayland-protocol-imx = "${libdir}/libgc_wayland_protocol*"
FILES_libgc-wayland-protocol-imx-dev = "${libdir}/libgc_wayland_protocol* ${libdir}/pkgconfig/gc_wayland_protocol.pc"

# Package libvivante-dri-imx
FILES_libvivante-dri-imx = "${libdir}/dri/vivante_dri.so"
RDEPENDS_libvivante-dri-imx = "libdrm"


# Package libvulkan (will not generate anything, there is still no libs)
FILES_libvulkan-imx = "${libdir}/vulkan/libvulkan_VSI${SOLIBS}"
FILES_libvulkan-imx-dev = "${includedir}/vulkan ${libdir}/vulkan/libvulkan_VSI${SOLIBSDEV}"


# Package imx-gpu-viv-tools
FILES_imx-gpu-viv-tools = "${bindir}/gmem_info"


# Package imx-gpu-viv-demos
FILES_imx-gpu-viv-demos = "/opt"
INSANE_SKIP_imx-gpu-viv-demos += "rpaths dev-deps"


# FIXME: Remove the following lines after adding libopenvx package
INSANE_SKIP_libclc-imx-dev = "dev-elf"
INSANE_SKIP_libopenvg-imx-dev = "dev-elf"
INSANE_SKIP_libopencl-imx-dev = "dev-elf"
INSANE_SKIP_libgbm-imx-dev = "dev-elf"
INSANE_SKIP_libgl-imx-dev = "dev-elf"
INSANE_SKIP_libglslc-imx-dev = "dev-elf"
