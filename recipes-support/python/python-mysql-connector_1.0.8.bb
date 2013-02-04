DESCRIPTION = "MySQL driver written in Python which does not depend on MySQL C client\
 libraries and implements the DB API v2.0 specification (PEP-249)."

HOMEPAGE = "http://dev.mysql.com/doc/connector-python/en/index.html"
AUTHOR = "Author: Oracle and/or its affiliates"
LICENSE = "GPLv2 & FOSS-License-Exception"
LIC_FILES_CHKSUM = "file://COPYING;md5=775a05bce622d94072e1b0dee1a200e2"

SRCNAME = "mysql-connector-python"
SRC_URI = "http://cdn.mysql.com/Downloads/Connector-Python/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "1f2dd335c72684d51ee5d34f127d7ca9"
SRC_URI[sha256sum] = "a2f1973ac5d5dc190b6e2688928afb542fd35d07b77e2217592a23b955cea6a3"

inherit distutils

S = "${WORKDIR}/${SRCNAME}-${PV}"
