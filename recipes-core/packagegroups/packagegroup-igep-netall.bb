DESCRIPTION = "Set of networking utilities packages for testing igep image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS_${PN} = " \
	ethtool \
	ifupdown \
	iproute2 \
	iputils-ping6 \
	iputils-traceroute6 \
	net-tools \
        inetutils-ping \
        inetutils-telnet \
        inetutils-tftp \
        inetutils-traceroute \
	wget \
"
