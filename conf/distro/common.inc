DISTRO_VERSION ??= "UNASSIGNED"
DISTRO_VERSION_mx6s = "i.MX6S"
DISTRO_VERSION_mx6dl = "i.MX6DL"
DISTRO_VERSION_mx6q = "i.MX6Q"
DISTRO_VERSION_mx6ul = "i.MX6UL"
DISTRO_VERSION_ti33x = "AM335x"
DISTRO_VERSION_dm3730 = "DM3730"

VENDOR = "isee"
TARGET_VENDOR = "-${VENDOR}"

FIRMWARE_VERSION= "v1.0"

# Some default locales
IMAGE_LINGUAS ?= "de-de fr-fr en-us es-es"

# Activate config checker
INHERIT += "sanity"

# Accept Freescale License
ACCEPT_FSL_EULA = "1" 

LICENSE_FLAGS_WHITELIST="commercial"
