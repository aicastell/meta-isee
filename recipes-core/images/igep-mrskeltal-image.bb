# If you see this recipe while surfing yocto's layers that means
# you have been visited by Mr Skeltal image of the succesfull build.
# Success in your bitbakes will come to you but only after you execute
# "bitbake igep-mrskeltal-image" in your command line.

# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# Include in rootfs
IMAGE_INSTALL += " \
        alsa-state \
        alsa-utils \
	packagegroup-igep-initscripts \
	kmod \
        kernel-modules \
	igep-tools \
	fbida \
        mtd-utils \
        mtd-utils-jffs2 \
        mtd-utils-misc \
        mtd-utils-ubifs \
	dosfstools \
        e2fsprogs \
        e2fsprogs-mke2fs \	
        util-linux \
        util-linux-mkfs \
	nano \	
"
