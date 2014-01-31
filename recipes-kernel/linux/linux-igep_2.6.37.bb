DESCRIPTION = "2.6 Linux Kernel for IGEP based platforms"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE_igep00x0 = "igep00x0"

inherit kernel kernel-arch

PR = "r7"
KV = "${PV}-7"

SRC_URI = "http://downloads.isee.biz/pub/releases/linux_kernel/v${KV}/linux-omap-${KV}.tar.gz"

SRC_URI[md5sum] = "4a5392c11c06e3eeb165a378165903f6"
SRC_URI[sha256sum] = "5e2e2c35457b77d1ecd3340f6db97fc1a9f3c660fbdfe308aef82b7ff4a89df3"

do_configure() {
	rm -f ${S}/.config || true
	oe_runmake igep00x0_defconfig
}

S = "${WORKDIR}/linux-omap-${KV}"
