DISTRO_VERSION ??= "UNASSIGNED"
DISTRO_VERSION_mx6dl = "i.MX6DL-${METADATA_BRANCH}"
DISTRO_VERSION_ti33x = "AM335x-${METADATA_BRANCH}"
VENDOR = "isee"
TARGET_VENDOR = "-${VENDOR}"

# Some default locales
IMAGE_LINGUAS ?= "de-de fr-fr en-us es-es"

# Activate config checker
INHERIT += "sanity"

# Accept Freescale License
ACCEPT_FSL_EULA = "1" 

LICENSE_FLAGS_WHITELIST="commercial"
