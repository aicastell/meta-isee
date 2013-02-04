DESCRIPTION = "Simple application written in QT to display web."
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "qt4-embedded"

SRC_URI = "git://git.isee.biz/pub/scm/extras/qtwebview.git;protocol=git"

SRCREV = "39ba856dc9c3d796ae35688fb08b162460a0be5c"

PV = "0.0"
PR = "r0"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
 	install -m 755 ${S}/qtwebview ${D}${bindir}
}

inherit qt4e
