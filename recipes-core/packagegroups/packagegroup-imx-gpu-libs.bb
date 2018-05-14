DESCRIPTION = "i.MX GPU libraries, includes and demos."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR = "r0"

inherit packagegroup

RDEPENDS_${PN} = " \
	libclc-imx \
	libegl-imx-dev \
	libgal-imx-dev \
	libgbm-imx-dev \
	libgl-imx \
	libgles-imx-dev \
	libgles2-imx-dev \
	libgles3-imx-dev \
	libglslc-imx \
	libopencl-imx-dev \
	libopenvg-imx-dev \
	libllvm-imx \
	libvdk-imx-dev \
	libvsc-imx \
	libvivante-dri-imx \
	libwayland-egl-imx-dev \
	libwayland-viv-imx-dev \
	libgc-wayland-protocol-imx \
	imx-gpu-viv-tools \
	imx-gpu-viv-demos \
"

