DESCRIPTION = "Suite of shell scripts and a set of helper programs for \
IGEP-technology based boards"
HOMEPAGE = "http://www.isee.biz"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

# This commit corresponds to tag 1.3+
# SRCREV = "9a4810cead5859314d8b341f0f7e8aea09495a43"
#SRCREV = "0ef027809d5e876f6b25deb064542081ec1bd643"

# Set autorev for development purposes
SRCREV = "${AUTOREV}"

SRCBRANCH="master"

PV = "1.3+git${SRCPV}"
PR = "r6"

inherit update-rc.d

INITSCRIPT_NAME = "igep-tools.sh"
INITSCRIPT_PARAMS = "start 98 S ."

SRC_URI = "git://git.isee.biz/igep-tools/igep-tools.git;protocol=https;branch=${SRCBRANCH}"

do_compile() {
	:
}

do_install() {
	install -d ${D}/lib/igep-tools
	install -m 0755 ${S}/scripts/e-functions ${D}/lib/igep-tools/e-functions
	install -d ${D}${bindir}
	install -m 0755 ${S}/scripts/igep-flash ${D}${bindir}
	install -m 0755 ${S}/scripts/igep-media-create ${D}${bindir}
        install -m 0755 ${S}/scripts/writeloader ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/init/rc.init ${D}${sysconfdir}/init.d/igep-tools.sh
}

S = "${WORKDIR}/git"

FILES_${PN} += "/lib"

