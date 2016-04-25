SUMMARY = "VMcore extraction tool"
DESCRIPTION = "This program is used to extract a subset of the memory available either \
via /dev/mem or /proc/vmcore (for crashdumps). It is used to get memory \
images without extra uneeded information (zero pages, userspace programs, etc)."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "zlib elfutils bzip2"

inherit binconfig

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "http://sourceforge.net/projects/makedumpfile/files/makedumpfile/${PV}/makedumpfile-${PV}.tar.gz;name=makedumpfile \
           file://0001-Fix-PAGESIZE-redefinition.patch \
          "

SRC_URI[makedumpfile.md5sum] = "e44244db888acdb0e75962bb59feace2"
SRC_URI[makedumpfile.sha256sum] = "47d16312b3226f6d1a1e6548e22c33d00e8851fedab793d97da8d3c0a6205d4a"

EXTRA_OEMAKE = "TARGET=${TARGET_ARCH}"

do_compile() {
    oe_runmake LINKTYPE=dynamic
}

do_install() {
    install -d ${D}${bindir}
    install -c -m 755 ${S}/makedumpfile ${D}${bindir}
}
