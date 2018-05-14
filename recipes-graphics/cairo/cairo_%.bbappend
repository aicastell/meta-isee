# Hardware EGL accelerated
CFLAGS_append_mx6 = " -DLINUX -DEGL_API_FB"

PACKAGECONFIG_append_imxgpu3d = " egl glesv2"
PACKAGECONFIG_remove = "x11 xcb"
