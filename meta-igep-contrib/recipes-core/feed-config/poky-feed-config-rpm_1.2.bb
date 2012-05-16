DESCRIPTION = "Poky feed configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = " file://isee.biz.repo"

do_install () {
	install -d ${D}${sysconfdir}/zypp/repos.d/
	install -m 0644  ${WORKDIR}/isee.biz.repo ${D}${sysconfdir}/zypp/repos.d/
}

FILES_${PN} = "${sysconfdir}/zypp/repos.d/ "
