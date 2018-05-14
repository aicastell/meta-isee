# Copyright (C) 2015 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Package group used by freescale to provide set of packages for GStreamer1.x Plugins"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

LICENSE_FLAGS_WHITELIST="commercial"

MACHINE_GSTREAMER_1_0_EXTRA_INSTALL ?= ""
MACHINE_GSTREAMER_1_0_PLUGIN ?= ""

RDEPENDS_${PN} += " \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-base-meta \
    ${MACHINE_GSTREAMER_1_0_EXTRA_INSTALL} \
    ${MACHINE_GSTREAMER_1_0_PLUGIN} \
"
#${MACHINE_GSTREAMER_1_0_PLUGIN} 

# Open source plugins specifiec by i.MX
IMX_GSTREAMER_1_0_EXTRA_INSTALL = " \
				    gstreamer1.0-libav \
                                    gstreamer1.0-plugins-bad-meta \
				    gstreamer1.0-plugins-ugly \
                                    gstreamer1.0-rtsp-server \
"

MACHINE_GSTREAMER_1_0_EXTRA_INSTALL_mx6 = "${IMX_GSTREAMER_1_0_EXTRA_INSTALL}"
MACHINE_GSTREAMER_1_0_EXTRA_INSTALL_mx6ul = "${IMX_GSTREAMER_1_0_EXTRA_INSTALL}"
MACHINE_GSTREAMER_1_0_EXTRA_INSTALL_mx7 = "${IMX_GSTREAMER_1_0_EXTRA_INSTALL}"
