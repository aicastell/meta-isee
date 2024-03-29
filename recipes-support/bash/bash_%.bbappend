FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
            file://.bashrc \
            file://.profile \
"

FILES_${PN}_append = " ${ROOT_HOME}"

do_install_append() {
    install -d ${D}${ROOT_HOME}
    install -m 0644 ${WORKDIR}/.bashrc ${D}${ROOT_HOME}/.bashrc
    install -m 0644 ${WORKDIR}/.profile ${D}${ROOT_HOME}/.profile
}
