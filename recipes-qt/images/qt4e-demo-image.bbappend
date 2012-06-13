
CORE_IMAGE_EXTRA_INSTALL_igep00x0 += "\
	igep-x-loader \
	qt4-embedded-plugin-gfxdriver-gfxpvregl \
	libqt-embeddedpvrqwswsegl4 \
	"

# Set the default Window System for integration with Qt/Embedded
rootfs_update_powervr_windowsystem () {
	echo "[default]" >${IMAGE_ROOTFS}/etc/powervr.ini
	echo "WindowSystem=libpvrQWSWSEGL.so.1" >>${IMAGE_ROOTFS}/etc/powervr.ini
}

ROOTFS_POSTPROCESS_COMMAND_igep00x0 += "rootfs_update_powervr_windowsystem ; "
