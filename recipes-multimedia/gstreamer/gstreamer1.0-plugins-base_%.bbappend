# Hardware EGL accelerated
CFLAGS_append_mx6 = " -DLINUX -DEGL_API_FB"

PACKAGECONFIG_remove = "x11"

