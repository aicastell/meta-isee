# Common configuration for all igep machines

# Add U-boot to building stage
EXTRA_IMAGEDEPENDS_igep0034 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0046dl += "u-boot-igep-imx"

GCCVERSION = "6.3%"
PREFERRED_VERSION_linux-libc-headers ?= "4.10"
PREFERRED_VERSION_linux-yocto ?= "3.2%"
KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.bz2 "
