# Base this image on core-image-sato
include recipes-sato/images/core-image-sato.bb

# Include modules in rootfs
IMAGE_INSTALL += " \
	kernel-modules \
	"
# Add IGEP Firmware
IMAGE_FEATURES += "splash"

