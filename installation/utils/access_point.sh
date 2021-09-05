#!/bin/bash
echo "#####################__Actualizando__"
sudo apt-get update && sudo apt-get -y upgrade

sudo apt -y install dnsmasq hostapd

sudo systemctl stop hostapd
sudo systemctl stop dnsmasq

sudo echo "interface=wlan0" >> /etc/hostapd/hostapd.conf
sudo echo "driver=nl80211" >> /etc/hostapd/hostapd.conf
sudo echo "ssid=WIFI_TAQUILLAS" >> /etc/hostapd/hostapd.conf
sudo echo "hw_mode=g" >> /etc/hostapd/hostapd.conf
sudo echo "channel=7" >> /etc/hostapd/hostapd.conf
sudo echo "wmm_enabled=0" >> /etc/hostapd/hostapd.conf
sudo echo "macaddr_acl=0" >> /etc/hostapd/hostapd.conf
sudo echo "auth_algs=1" >> /etc/hostapd/hostapd.conf
sudo echo "ignore_broadcast_ssid=0" >> /etc/hostapd/hostapd.conf
sudo echo "wpa=2" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_passphrase=wifi7aqui11as" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_key_mgmt=WPA-PSK" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_pairwise=TKIP" >> /etc/hostapd/hostapd.conf
sudo echo "rsn_pairwise=CCMP" >> /etc/hostapd/hostapd.conf
sudo echo "" >> /etc/hostapd/hostapd.conf

sudo echo "" >> /etc/default/hostapd
sudo echo "DAEMON_CONF=\"/etc/hostapd/hostapd.conf\"" >> /etc/default/hostapd

sudo systemctl unmask hostapd
sudo systemctl enable hostapd

sudo mv /etc/dnsmasq.conf /etc/dnsmasq.conf.orig
sudo echo "interface=wlan0 # Listening interface" >> /etc/dnsmasq.conf
sudo echo "domain-needed" >> /etc/dnsmasq.conf
sudo echo "bogus-priv" >> /etc/dnsmasq.conf
sudo echo "dhcp-range=192.168.4.3,192.168.4.20,255.255.255.0,24h" >> /etc/dnsmasq.conf

sudo echo "" >> /etc/dhcpcd.conf
sudo echo "nohook wpa_supplicant" >> /etc/dhcpcd.conf
sudo echo "interface wlan0" >> /etc/dhcpcd.conf
sudo echo "static ip_address=192.168.4.2/24" >> /etc/dhcpcd.conf
sudo echo "static routers=192.168.50.1" >> /etc/dhcpcd.conf

#sudo systemctl reboot

echo "Reinicia el sistema para aplicar los cambios."


