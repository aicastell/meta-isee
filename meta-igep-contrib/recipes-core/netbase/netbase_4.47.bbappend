FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.1"

# On igep00x0 starting at 40 does not up the WLAN
INITSCRIPT_PARAMS_igep00x0 = "start 80 S . stop 40 0 6 1 ."

SRC_URI_append_igep00x0 = " file://if-pre-up.d-wlan"

do_install_append_igep00x0() {
	install -m 0755 ${WORKDIR}/if-pre-up.d-wlan ${D}${sysconfdir}/network/if-pre-up.d/wireless
}
