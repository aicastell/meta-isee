DESCRIPTION = "Minimal test of packages for an operative igep image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS_${PN} = " \
	cronie \
	sqlite3 \
	igep-tools\
	ntp \
	ntpdate \
	python \
	python-pip \
	python-pyserial \
"
