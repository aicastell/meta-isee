SUMMARY = "ALSA sound library"
HOMEPAGE = "http://www.alsa-project.org"
BUGTRACKER = "http://alsa-project.org/main/index.php/Bug_Tracking"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1 & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34 \
                    file://src/socket.c;beginline=1;endline=26;md5=11ff89a8a7a4a690a5c78effe8159545"

BBCLASSEXTEND = "native nativesdk"

SRC_URI = "ftp://ftp.alsa-project.org/pub/lib/${BP}.tar.bz2 \
           file://Check-if-wordexp-function-is-supported.patch \
           file://avoid-including-sys-poll.h-directly.patch \
"
SRC_URI[md5sum] = "1946e6438b8262a7b8fdadacd0e06ba7"
SRC_URI[sha256sum] = "d38dacd9892b06b8bff04923c380b38fb2e379ee5538935ff37e45b395d861d6"

inherit autotools pkgconfig

require alsa-fpu.inc
EXTRA_OECONF += "${@get_alsa_fpu_setting(bb, d)} "

EXTRA_OECONF += "--disable-python"

EXTRA_OECONF_append_libc-uclibc = " --with-versioned=no "

PACKAGES =+ "alsa-server libasound alsa-conf alsa-doc"
FILES_libasound = "${libdir}/libasound.so.*"
FILES_alsa-server = "${bindir}/*"
FILES_alsa-conf = "${datadir}/alsa/"

RDEPENDS_libasound = "alsa-conf"

# alsa-lib gets automatically added to alsa-lib-dev dependencies, but the
# alsa-lib package doesn't exist. libasound is the real library package.
RDEPENDS_${PN}-dev = "libasound"

# upgrade path
RPROVIDES_${PN}-dev = "alsa-dev"
RREPLACES_${PN}-dev = "alsa-dev"
RCONFLICTS_${PN}-dev = "alsa-dev"

RPROVIDES_alsa-conf = "alsa-conf-base"
RREPLACES_alsa-conf = "alsa-conf-base"
RCONFLICTS_alsa-conf = "alsa-conf-base"
