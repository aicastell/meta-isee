FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_igep00x0 = "igep00x0"

SRC_URI += " file://0001-wsegl2-support.patch "

DEPENDS += "opengles-sgx-530"

QT_GLFLAGS = "-opengl es2 -depths 16,24,32  -plugin-gfx-powervr"

QT_CONFIG_FLAGS += " \
 -exceptions \
"
