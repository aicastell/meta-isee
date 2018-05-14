# Base this image on igep-image-minimal
include recipes-core/images/igep-minimal-image.bb

IMAGE_FEAUTRES += " x11-base"

# Include in rootfs
IMAGE_INSTALL += " \
	packagegroup-gstreamer1.0-core \
	packagegroup-pulseaudio \	
	packagegroup-qt5 \
	packagegroup-qt5-demos \
"

