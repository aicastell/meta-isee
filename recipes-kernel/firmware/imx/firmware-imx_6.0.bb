# Copyright (C) 2015 PHYTEC Messtechnik GmbH,
# Author: Stefan Christ <s.christ@phytec.de>

inherit fsl-bin-unpack

SUMMARY = "Freescale i.MX firmware files"
DESCRIPTION = "Freescale i.MX firmware files for the VPU and SDMA"
SECTION = "base"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=6b552f505eedab4a11ab538cf3db743a"

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-bin=true" 
SRC_URI[md5sum] = "088fb08b565748b537f6481b1ad6c9d7" 
SRC_URI[sha256sum] = "9fa7c204a6ff8a30f2b5e8f9002d8c5736791e455dc137b952fa725dc0c3aeb8"

PACKAGES += " \
    ${PN}-freescale-imx-license \
    ${PN}-vpu-mx6q \
    ${PN}-vpu-mx6dl \
    ${PN}-sdma-mx6q \
    ${PN}-epdc \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {

    install -d ${D}${base_libdir}/firmware
    install -d ${D}${base_libdir}/firmware/vpu
    install -d ${D}${base_libdir}/firmware/sdma
    install -d ${D}${base_libdir}/firmware/epdc

    # i.MX6 VPU firmware.
    install -m 755 ${S}/firmware/vpu/vpu_fw_imx6q.bin ${D}${base_libdir}/firmware/vpu/
    install -m 755 ${S}/firmware/vpu/vpu_fw_imx6d.bin ${D}${base_libdir}/firmware/vpu/
    # i.MX6 VPU firmware.
    install -m 755 ${S}/firmware/sdma/sdma-imx6q.bin ${D}${base_libdir}/firmware/sdma/
    # i.MX6 EPDC firmware
    cp -R ${S}/firmware/epdc/* ${D}${base_libdir}/firmware/epdc/
    # Freescale License
    cp -R ${S}/COPYING ${D}${base_libdir}/firmware/vpu/
    cp -R ${S}/COPYING ${D}${base_libdir}/firmware/sdma/
    cp -R ${S}/COPYING ${D}${base_libdir}/firmware/epdc/
}

FILES_${PN}-freescale-imx-license = " \
	${base_libdir}/firmware/vpu/COPYING \
        ${base_libdir}/firmware/sdma/COPYING \
        ${base_libdir}/firmware/epdc/COPYING \
"
FILES_${PN}-vpu-mx6q = "${base_libdir}/firmware/vpu/vpu_fw_imx6q.bin"
FILES_${PN}-vpu-mx6dl = "${base_libdir}/firmware/vpu/vpu_fw_imx6d.bin"
FILES_${PN}-sdma-mx6q = "${base_libdir}/firmware/sdma/sdma-imx6q.bin"
FILES_${PN}-epdc = "${base_libdir}/firmware/epdc/*"
