DESCRIPTION = "Suite of shell scripts and a set of helper programs for \
IGEP-technology based boards"
HOMEPAGE = "http://www.isee.biz"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "a5536f9cdc02a4592eb40b2ff400c40a56744b3d"

PV = "1.0+git${SRCPV}"
PR = "r2"

inherit update-rc.d

INITSCRIPT_NAME = "igep-tools.sh"
INITSCRIPT_PARAMS = "start 98 S ."

SRC_URI = "git://git.isee.biz/pub/scm/igep-tools.git;protocol=git"

do_compile() {
	:
}

do_install() {
	install -d ${D}/lib/igep-tools
	install -m 0755 ${S}/scripts/e-functions ${D}/lib/igep-tools/e-functions
	install -d ${D}${bindir}
	install -m 0755 ${S}/scripts/igep-flash ${D}${bindir}
	install -m 0755 ${S}/scripts/igep-media-create ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/init/rc.init ${D}${sysconfdir}/init.d/igep-tools.sh
}

S = "${WORKDIR}/git"

FILES_${PN} += "/lib"
