DESCRIPTION = "Linux kernel for SOPA0000 AM335x"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "sopa0000"

PREMIRRORS = ""
MIRRORS = ""

inherit kernel kernel-arch

S = "${WORKDIR}/git"

SRCBRANCH = "isee-linux-v.4.9.y-SOPA0000"

KCONFIG_MODE ?= "--alldefconfig"
KBUILD_DEFCONFIG_sopa0000 ?= "am335x_sopa0000_defconfig"

# make[3]: *** [scripts/extract-cert] Error 1
DEPENDS += "openssl-native"
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"

PR = "r0"
PV = "${LINUX_VERSION}"

LINUX_VERSION ?= "4.9.81"

# Set autorev for development purposes
SRCREV = "${AUTOREV}"

SRC_URI = "git://git@git.isee.biz/linux-kernel/linux-omap-2.6.git;protocol=ssh;branch=${SRCBRANCH}"

# Support for yocto to search defconfig in arch/arm/configs/
require recipes-kernel/linux/linux-yocto.inc

