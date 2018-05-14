DESCRIPTION = "U-Boot for IGEP PROCESSOR BOARDS"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

require recipes-bsp/u-boot/u-boot.inc

COMPATIBLE_MACHINE = "igep0020|igep0033|igep0034"

PREMIRRORS = ""
MIRRORS = ""

# Don't make this the default kernel, right now it is only for testing purposes
DEFAULT_PREFERENCE = "-1"

# Set autorev for development purposes
SRCREV = "${AUTOREV}"

# Set latest stable branch
SRCBRANCH = "isee_v2017.03"

PV = "2017.03"
PR = "r0"

S = "${WORKDIR}/git"

SRC_URI = "git://git@git.isee.biz/arm-boot/u-boot-arm.git;protocol=ssh;branch=${SRCBRANCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EXTRA_OEMAKE += "-d"
