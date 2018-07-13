DESCRIPTION = "Mount partition boot into /boot folder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://igep-boot-mount.sh"

S = "${WORKDIR}"

# We need to comment yocto helpers and do it manually so that skeltal get it too
#inherit update-rc.d
#INITSCRIPT_NAME = "igep-boot-mount.sh"
#INITSCRIPT_PARAMS = "start 92 1 ."

do_install () {
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/rcS.d
    install -m 755 ${S}/igep-boot-mount.sh ${D}/${sysconfdir}/init.d/igep-boot-mount.sh
    cd ${D}/${sysconfdir}/rcS.d
    ln -sf ../init.d/igep-boot-mount.sh S80igep-boot-mount.sh
}
