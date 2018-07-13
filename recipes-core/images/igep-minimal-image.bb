# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# Include in rootfs
IMAGE_INSTALL += " \
	kernel-modules \
	packagegroup-igep-initscripts \
	packagegroup-igep-minimal \
	packagegroup-igep-testtools \
"
