DESCRIPTION = "Linux kernel for IGEP PROCESSOR BOARDS"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "igep0046dl|igep0046q|igep0146"

PREMIRRORS = ""
MIRRORS = ""

inherit kernel kernel-arch

S = "${WORKDIR}/git"

SRCBRANCH = "isee-imx_4.9.11_1.0.0_ga"

KCONFIG_MODE ?= "--alldefconfig"
KBUILD_DEFCONFIG_igep0046dl ?= "imx6_igep0046_defconfig"
KBUILD_DEFCONFIG_igep0046q ?= "imx6_igep0046_defconfig"
KBUILD_DEFCONFIG_igep0146 ?= "imx6_igep0046_defconfig"


# make[3]: *** [scripts/extract-cert] Error 1
DEPENDS += "openssl-native"
HOST_EXTRACFLAGS += "-I${STAGING_INCDIR_NATIVE}"

PR = "r0"
PV = "${LINUX_VERSION}"

LINUX_VERSION ?= "4.9.11"

# Set autorev for development purposes
SRCREV = "${AUTOREV}"

SRC_URI = "git://git@git.isee.biz/linux-kernel/linux-imx.git;protocol=ssh;branch=${SRCBRANCH}"

# Support for yocto to search defconfig in arch/arm/configs/
require recipes-kernel/linux/linux-yocto.inc

#RPROVIDES_kernel-image += "kernel-module-imx-gpu-viv"
