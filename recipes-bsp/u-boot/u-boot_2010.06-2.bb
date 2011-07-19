require u-boot.inc

# No patches for other machines yet
COMPATIBLE_MACHINE = "(igep0020)"

DEFAULT_PREFERENCE_igep0020 = "1"

SRC_URI = "git://git.igep.es/pub/scm/u-boot-arm.git;protocol=git \
          "

# v2010.06-2 tag -> ccba4652f9d2bd32e76770661d5b5762631f2261
SRCREV = "ccba4652f9d2bd32e76770661d5b5762631f2261"
PR = "r1"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git"
