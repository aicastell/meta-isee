# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

# Include in rootfs
IMAGE_INSTALL += " \
	kernel-modules \
	devmem2 \
        igep-tools \
	iperf \
	memtester \
	packagegroup-igep-initscripts \
        alsa-state \
        alsa-utils \
        bridge-utils \
        bzip2 \
        dosfstools \
        e2fsprogs \
        e2fsprogs-mke2fs \
        fbida \
        hostapd \
        i2c-tools \
        icu \
        iw \
        kmod \
        ldd \
        libnl \
        libnl-dev \
        libnl-cli \
        libnl-genl \
        libnl-nf \
        mmc-utils \
        mtd-utils \
        mtd-utils-jffs2 \
        mtd-utils-misc \
        mtd-utils-ubifs \
        nano \
        openssh \
        opkg \
        parted \
        psplash \
        python-smbus \
        rfkill \
        sqlite3 \
        strace \
        usbutils \
        util-linux \
        util-linux-mkfs \
        wpa-supplicant \
	ethtool \
	ifupdown \
	iproute2 \
	iputils-ping6 \
	iputils-traceroute6 \
	net-tools \
        inetutils-ping \
        inetutils-tftp \
        inetutils-traceroute \
	wget \
"

