DESCRIPTION = "Minimal test of packages for an operative igep image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS_${PN} = " \
        alsa-state \
        alsa-utils \
        bridge-utils \
	bzip2 \
	cmake \	
        dhcp-client \
        e2fsprogs \
        e2fsprogs-mke2fs \
        fbida \
        hostapd \
        i2c-tools \
        icu \
        iw \
        ldd \
        minicom \
        mtd-utils \
        mtd-utils-jffs2 \
        mtd-utils-misc \
        mtd-utils-ubifs \
        nano \
        openssh \
        opkg \
        packagegroup-core-buildessential \
	psplash \
        python-smbus \
        rfkill \
        sqlite3 \
        strace \
        usbutils \
        util-linux \
        util-linux-mkfs \
        wpa-supplicant \
"
