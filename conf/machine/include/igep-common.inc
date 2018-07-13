# Common configuration for all igep machines

# Add U-boot to building stage
EXTRA_IMAGEDEPENDS_sopa0000 += "u-boot-igep-sopa"
EXTRA_IMAGEDEPENDS_igep0020 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0020hf += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0030 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0032 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0033 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0034 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0046dl += "u-boot-igep-imx"
EXTRA_IMAGEDEPENDS_igep0046q += "u-boot-igep-imx"
EXTRA_IMAGEDEPENDS_igep0146 += "u-boot-igep-imx"

GCCVERSION = "6.3%"
PREFERRED_VERSION_linux-libc-headers ?= "4.10"
PREFERRED_VERSION_linux-yocto ?= "3.2%"
KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.bz2"

# Don't include kernels in standard images
RDEPENDS_kernel-base = ""
