# Hardware EGL accelerated
CFLAGS_append_mx6 = " -DLINUX -DEGL_API_FB"

PACKAGECONFIG[g2d] = ",--disable-g2d,imx-gpu-viv-isee"
PACKAGECONFIG[g2dpango] = ",--disable-g2dpango,imx-gpu-viv-isee pango"
PACKAGECONFIG_remove = "x11"

PACKAGECONFIG += " eglvivsink"
