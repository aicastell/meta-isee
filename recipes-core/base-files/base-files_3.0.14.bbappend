FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

require conf/distro/release.inc

PR := "${PR}.${POKY_SUBVERSION}"

BASEFILESISSUEINSTALL_igep00x0 = "do_basefilesissue"

do_basefilesissue () {
	if [ -n "${MACHINE}" -a "${hostname}" = "openembedded" ]; then
		echo ${MACHINE} > ${D}${sysconfdir}/hostname
	else
		echo ${hostname} > ${D}${sysconfdir}/hostname
	fi

	install -m 644 ${WORKDIR}/issue*  ${D}${sysconfdir}
	  
	echo -n "Poky Linux " >> ${D}${sysconfdir}/issue
	echo -n "Poky Linux " >> ${D}${sysconfdir}/issue.net
	echo -n "${POKY_VERSION}-${POKY_SUBVERSION}${EXTRAVERSION} " >> ${D}${sysconfdir}/issue
	echo -n "${POKY_VERSION}-${POKY_SUBVERSION}${EXTRAVERSION} " >> ${D}${sysconfdir}/issue.net
	echo -n "(denzil) " >> ${D}${sysconfdir}/issue
	echo -n "(denzil) " >> ${D}${sysconfdir}/issue.net
	echo "\n \l" >> ${D}${sysconfdir}/issue
	echo >> ${D}${sysconfdir}/issue
	echo "%h"    >> ${D}${sysconfdir}/issue.net
	echo >> ${D}${sysconfdir}/issue.net
}
