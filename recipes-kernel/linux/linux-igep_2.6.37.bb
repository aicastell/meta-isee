DESCRIPTION = "2.6 Linux Kernel for IGEP based platforms"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE_igep00x0 = "igep00x0"

inherit kernel kernel-arch

PR = "r3"
KV = "${PV}-3"

SRC_URI = "http://downloads.isee.biz/pub/releases/linux_kernel/v${KV}/linux-omap-${KV}.tar.gz"

SRC_URI[md5sum] = "fd5b4af05d17ffcdf61b6d5a47e46fbc"
SRC_URI[sha256sum] = "cf87017db008da37e7f3675eae9e4bfe0b45e49f06e8637eeca4c7a647ddc49c"

do_configure() {
	rm -f ${S}/.config || true
	oe_runmake igep00x0_defconfig
}

S = "${WORKDIR}/linux-omap-${KV}"
