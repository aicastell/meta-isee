DESCRIPTION = "Umount partition boot into /boot folder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://igep-boot-umount.sh"

S = "${WORKDIR}"

# We need to comment yocto helpers and do it manually so that skeltal get it too
#inherit update-rc.d
#INITSCRIPT_NAME = "igep-boot-umount.sh"
#INITSCRIPT_PARAMS = "start 80 0 6 ."

do_install () {
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/rc0.d
    install -d ${D}/${sysconfdir}/rc6.d
    install -m 755 ${S}/igep-boot-umount.sh ${D}/${sysconfdir}/init.d/igep-boot-umount.sh
    cd ${D}/${sysconfdir}/rc0.d
    ln -sf ../init.d/igep-boot-umount.sh K80igep-boot-umount.sh
    cd ${D}/${sysconfdir}/rc6.d
    ln -sf ../init.d/igep-boot-umount.sh K80igep-boot-umount.sh
}
