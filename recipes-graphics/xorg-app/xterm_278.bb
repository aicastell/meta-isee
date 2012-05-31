require recipes-graphics/xorg-app/xorg-app-common.inc
DESCRIPTION = "xterm is the standard terminal emulator for the X Window System."
DEPENDS = "libxaw xproto xextproto libxext libxau libxpm ncurses"
PR = "r6"

LIC_FILES_CHKSUM = "file://xterm.h;beginline=3;endline=33;md5=71d7532e485b8d325906bd751617e2a4"

SRC_URI = "ftp://invisible-island.net/xterm/${PN}-${PV}.tgz"
SRC_URI[md5sum] = "3eeddfe35cb0a2db1924cfe0c20be443"
SRC_URI[sha256sum] = "1372f9afe07bc35bfd47482db146c649223dadd0b472da31f8c337ab37f90585"

EXTRA_OECONF = " --x-includes=${STAGING_INCDIR} \
                 --x-libraries=${STAGING_LIBDIR} \
                 FREETYPE_CONFIG=${STAGING_BINDIR_CROSS}/freetype-config \
                 --disable-imake \
                 --disable-setuid"

do_configure() {
        sed -e "s%/usr/contrib/X11R6%${STAGING_LIBDIR}%g" -i configure
        oe_runconf
}

FILES_${PN} += " /usr/lib/X11"
