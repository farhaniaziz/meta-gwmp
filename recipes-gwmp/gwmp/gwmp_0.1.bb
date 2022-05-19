
DESCRIPTION = "Gateway MultiProtocol PFE Project"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/farhaniaziz/gwmultiprotocol.git;protocol=https;branch=master"
SRCREV = "275b31e7844b7dd585566fabce42fe72bfbb3ba1"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "\
    python3-grpcio \
    python3-paho-mqtt \
    python3-protobuf \
    pymodbus \
    python3-pickle \
    python3-pyserial \
"

do_install() {
    # Install directories
    install -d ${D}/opt/gwmp/utils
    install -d ${D}/etc/gwmp
    install -d ${D}${systemd_unitdir}/system/

    # Install files
    install -m 0644 ${S}/systemd/gw_mp_config_manager.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/systemd/gw_mp_mqtt_manager.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/systemd/gw_mp_modbus_client.service ${D}${systemd_unitdir}/system

    install -m 0644 ${S}/config/config.json ${D}/etc/gwmp
    install -m 0644 ${S}/*.py ${D}/opt/gwmp
    install -m 0644 ${S}/utils/*.py ${D}/opt/gwmp/utils
}

FILES_${PN} = "/opt/* /etc/* ${systemd_unitdir}/*"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "gw_mp_config_manager.service gw_mp_mqtt_manager.service gw_mp_modbus_client.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"