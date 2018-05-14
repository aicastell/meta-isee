DESCRIPTION = "Firmware needed to support wifi peripherial"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI += " \
    file://wl18xx-fw-4.bin \
"

do_install_append(){
    install -d ${D}/lib/firmware/ti-connectivity/
    install -m 755 ${WORKDIR}/wl18xx-fw-4.bin ${D}${base_libdir}/firmware/ti-connectivity/
}

FILES_${PN} += " \
        ${base_libdir}/firmware/ti-connectivity/wl18xx-fw-4.bin \
"
