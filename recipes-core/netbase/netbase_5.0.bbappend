FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.5"

# On igep00x0 starting at 40 does not up the WLAN
INITSCRIPT_PARAMS_igep00x0 = "start 80 S . stop 40 0 6 1 ."

SRC_URI_append_igep00x0 = " \
	file://if-pre-up.d-wlan \
	file://ethalias.sh \
"

do_install_append_igep00x0() {
	install -m 0755 ${WORKDIR}/if-pre-up.d-wlan ${D}${sysconfdir}/network/if-pre-up.d/wireless
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/ethalias.sh ${D}${sysconfdir}/init.d/
	for i in 2 3 4 5; do
		install -d ${D}${sysconfdir}/rc${i}.d/
		ln -snf ${sysconfdir}/init.d/ethalias.sh ${D}${sysconfdir}/rc${i}.d/S50ethalias.sh
	done
}
