# Provides the i.MX common settings

# Common options
require conf/machine/include/igep-common.inc

# Set specific make target and binary suffix
PREFERRED_PROVIDER_u-boot = "u-boot-igep-imx"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-igep-imx"

# Handle default kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-igep-imx"
KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.bz2 "

# Ship kernel modules
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " kernel-modules"

# Ship i.MX Firmware
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS_mx6dl += " \
	firmware-imx-vpu-mx6dl \
	firmware-imx-vpu-mx6q \
	firmware-imx-sdma-mx6q \
        imx-gpu-g2d \
"

# Ship i.MX Firmware
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS_mx6q += " \
        firmware-imx-vpu-mx6dl \
        firmware-imx-vpu-mx6q \
        firmware-imx-sdma-mx6q \
        imx-gpu-g2d \
"

# Ship GPU Hw accelerated libraries
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS_mx6dl += " packagegroup-imx-gpu-libs"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS_mx6q += " packagegroup-imx-gpu-libs"

# Default Feautres
MACHINE_FEATURES = "usbgadget usbhost vfat alsa touchscreen"

# Add the ability to specify _imx machines
MACHINEOVERRIDES =. "imx:"

UBOOT_MAKE_TARGET ?= "u-boot.imx"
UBOOT_SUFFIX ?= "imx"

UBOOT_ENTRYPOINT_mx6  = "0x10008000"
UBOOT_ENTRYPOINT_mx6sl = "0x80008000"
UBOOT_ENTRYPOINT_mx6sll = "0x80008000"
UBOOT_ENTRYPOINT_mx6sx = "0x80008000"
UBOOT_ENTRYPOINT_mx6ul  = "0x10008000"
UBOOT_ENTRYPOINT_mx6ull  = "0x10008000"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER_DRIVER = "xf86-video-fbdev"
XSERVER_DRIVER_imxgpu2d = "xf86-video-imxfb-vivante"
XSERVER_DRIVER_vf = "xf86-video-modesetting"
XSERVER_DRIVER_use-mainline-bsp = "xf86-video-armada"
XSERVER = "xserver-xorg \
           xf86-input-evdev \
           ${XSERVER_DRIVER}"

# Tunes for hard/soft float-point selection. Note that we allow building for
# thumb support giving distros the chance to enable thumb by setting
# ARM_INSTRUCTION_SET = "thumb"
#
# handled by software
# DEFAULTTUNE_mx6 ?= "cortexa9t-neon"
# handled by hardware
DEFAULTTUNE_mx6 ?= "cortexa9thf-neon"
DEFAULTTUNE_mx6ul ?= "cortexa7thf-neon"
DEFAULTTUNE_mx6ull ?= "cortexa7thf-neon"

INHERIT += "machine-overrides-extender"

MACHINEOVERRIDES_EXTENDER_mx6q  = "imxipu:imxvpu:imxgpu2d:imxgpu3d"
MACHINEOVERRIDES_EXTENDER_mx6dl = "imxpxp:imxipu:imxvpu:imxgpu2d:imxgpu3d:imxepdc"
MACHINEOVERRIDES_EXTENDER_mx6sx = "imxpxp:imxgpu2d:imxgpu3d"
MACHINEOVERRIDES_EXTENDER_mx6sl = "imxpxp:imxgpu2d:imxepdc"
MACHINEOVERRIDES_EXTENDER_mx6sll  = "imxpxp:imxepdc"
MACHINEOVERRIDES_EXTENDER_mx6ul = "imxpxp"
MACHINEOVERRIDES_EXTENDER_mx6ull = "imxpxp:imxepdc"

MACHINEOVERRIDES_EXTENDER_FILTER_OUT_use-mainline-bsp = " \
    mx6 \
    mx6q \
    mx6dl \
    mx6sx \
    mx6sl \
    mx6ul \
    mx6ull \
"

# Sub-architecture support
MACHINE_SOCARCH_SUFFIX ?= ""
MACHINE_SOCARCH_SUFFIX_mx6q = "-mx6qdl"
MACHINE_SOCARCH_SUFFIX_mx6dl = "-mx6qdl"
MACHINE_SOCARCH_SUFFIX_mx6sx = "-mx6sx"
MACHINE_SOCARCH_SUFFIX_mx6sl = "-mx6sl"
MACHINE_SOCARCH_SUFFIX_mx6sll= "-mx6sll"
MACHINE_SOCARCH_SUFFIX_mx6ul = "-mx6ul"
MACHINE_SOCARCH_SUFFIX_mx6ull = "-mx6ul"
MACHINE_SOCARCH_SUFFIX_use-mainline-bsp = "-imx"

MACHINE_ARCH_FILTER = "virtual/kernel"
MACHINE_SOCARCH_FILTER_append_imx = " alsa-lib"
MACHINE_SOCARCH_FILTER_append_imxvpu = " imx-vpu libimxvpuapi imx-codec imx-vpuwrap imx-parser"
MACHINE_SOCARCH_FILTER_append_imxgpu2d = " virtual/libopenvg virtual/libgles1 virtual/libgles2 virtual/egl virtual/mesa virtual/libgl virtual/libg2d gstreamer1.0-plugins-bad"
MACHINE_SOCARCH_FILTER_append_imxpxp = " imx-codec imx-parser"
MACHINE_SOCARCH_FILTER_append_use-mainline-bsp = " \
    qtbase \
    virtual/libopenvg virtual/libgles1 virtual/libgles2 virtual/egl virtual/mesa virtual/libgl virtual/libg2d \
"

INHERIT += "fsl-dynamic-packagearch"

# Firmware
#MACHINE_FIRMWARE ?= ""
#MACHINE_FIRMWARE_append_mx6q = " linux-firmware-imx-sdma-imx6q firmware-imx-vpu-imx6q"
#MACHINE_FIRMWARE_append_mx6dl = " linux-firmware-imx-sdma-imx6q firmware-imx-vpu-imx6d firmware-imx-epdc"
#MACHINE_FIRMWARE_append_mx6ull = " firmware-imx-epdc"
#MACHINE_EXTRA_RRECOMMENDS += "${MACHINE_FIRMWARE}"

# Extra audio support
#MACHINE_EXTRA_RRECOMMENDS_append_mx6dl = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6q = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6sl = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6sll = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6sx = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6ul = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"
#MACHINE_EXTRA_RRECOMMENDS_append_mx6ull = " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'imx-alsa-plugins', '', d)}"

# Extra udev rules
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += " udev-rules-imx"

# GStreamer 1.0 plugins
MACHINE_GSTREAMER_1_0_PLUGIN ?= ""
MACHINE_GSTREAMER_1_0_PLUGIN_mx6dl ?= "gstreamer1.0-plugins-imx-meta"
MACHINE_GSTREAMER_1_0_PLUGIN_mx6q ?= "gstreamer1.0-plugins-imx-meta"
MACHINE_GSTREAMER_1_0_PLUGIN_mx6sl ?= "gstreamer1.0-plugins-imx-meta"
MACHINE_GSTREAMER_1_0_PLUGIN_mx6sx ?= "gstreamer1.0-plugins-imx-meta"
MACHINE_GSTREAMER_1_0_PLUGIN_mx6ul ?= "gstreamer1.0-plugins-imx-meta"
MACHINE_GSTREAMER_1_0_PLUGIN_mx6ull ?= "gstreamer1.0-plugins-imx-meta"

# mx6 GPU libraries
PREFERRED_PROVIDER_virtual/egl_mx6q ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/egl_mx6dl ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/egl_mx6sx ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/egl_mx6sl ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/egl_mx6sll ?= "mesa"
PREFERRED_PROVIDER_virtual/egl_mx6ul ?= "mesa"
PREFERRED_PROVIDER_virtual/egl_mx6ull ?= "mesa"

PREFERRED_PROVIDER_virtual/libgles1_mx6q ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles1_mx6dl ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles1_mx6sx ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles1_mx6sl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles1_mx6sll ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles1_mx6ul ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles1_mx6ull ?= "mesa"

PREFERRED_PROVIDER_virtual/libgles2_mx6q ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles2_mx6dl ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles2_mx6sx ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgles2_mx6sl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles2_mx6sll ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles2_mx6ul ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles2_mx6ull ?= "mesa"

PREFERRED_PROVIDER_virtual/libgl_mx6q ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgl_mx6dl ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgl_mx6sx ?= "imx-gpu-viv-isee"
PREFERRED_PROVIDER_virtual/libgl_mx6sl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgl_mx6sll ?= "mesa"
PREFERRED_PROVIDER_virtual/libgl_mx6ul ?= "mesa"
PREFERRED_PROVIDER_virtual/libgl_mx6ull ?= "mesa"

PREFERRED_PROVIDER_virtual/libg2d_mx6 ?= "imx-gpu-g2d"
PREFERRED_PROVIDER_virtual/libg2d_mx6sll ?= ""
PREFERRED_PROVIDER_virtual/libg2d_mx6ul = ""
PREFERRED_PROVIDER_virtual/libg2d_mx6ull = ""

# Comment out the 2 lines below to use bluez4.
DISTRO_FEATURES_append_mx6 = " bluez5"
DISTRO_FEATURES_append_mx6ul = " bluez5"
DISTRO_FEATURES_append_mx7 = " bluez5"
