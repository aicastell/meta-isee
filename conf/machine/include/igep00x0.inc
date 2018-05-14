#@TYPE: Common Machine
#@NAME: IGEP common machine
#@DESCRIPTION: Common machine configuration for the IGEP OMAP3 boards
#@MAINTAINER: Jose Miguel Sanchez Sanabria <jsanabria@iseebcn.com>

# Common options
require conf/machine/include/igep-common.inc

PREFERRED_PROVIDER_virtual/xserver ?= "xserver-xorg-lite"
XSERVER ?= "xserver-xorg-lite \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-video-omapfb \
           xf86-input-keyboard"

# Ship all kernel modules by default
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules linux-firmware-sd8686"

# U-Boot building for arch
UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_SUFFIX ?= "img"
SPL_BINARY ?= "MLO"

include conf/machine/include/tune-cortexa8.inc

# 2.6.37 and later kernels use OMAP_SERIAL, ttyO2 earlier kernels use ttyS2
SERIAL_CONSOLE ?= "115200 ttyO2"

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-igep"
PREFERRED_VERSION_u-boot-igep ?= "2017.03"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-igep"
PREFERRED_VERSION_linux-igep ?= "4.9.81"

MACHINE_FEATURES = "kernel26 usbgadget usbhost vfat alsa wifi bluetooth touchscreen"
