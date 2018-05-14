# Base this image on core-image-minimal
include recipes-core/images/igep-minimal-image.bb

# Add U-boot to building stage
EXTRA_IMAGEDEPENDS_igep0034 += "u-boot-igep"
EXTRA_IMAGEDEPENDS_igep0046dl += "u-boot-igep-imx"


# Include in rootfs
IMAGE_INSTALL += " \
	"

