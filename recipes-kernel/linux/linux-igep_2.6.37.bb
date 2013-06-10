DESCRIPTION = "2.6 Linux Kernel for IGEP based platforms"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE_igep00x0 = "igep00x0"

inherit kernel kernel-arch

PR = "r6"
KV = "${PV}-6"

SRC_URI = "http://downloads.isee.biz/pub/releases/linux_kernel/v${KV}/linux-omap-${KV}.tar.gz"

SRC_URI[md5sum] = "fdf22fda74b7401bb192e8ae1926b5b8"
SRC_URI[sha256sum] = "edf209fd702fb891595408654137a31b06173fb989afbe95a70919c21f54d406"

do_configure() {
	rm -f ${S}/.config || true
	oe_runmake igep00x0_defconfig
}

S = "${WORKDIR}/linux-omap-${KV}"
