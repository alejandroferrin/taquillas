#!/bin/bash
sudo apt -y install hostapd
sudo systemctl unmask hostapd
sudo systemctl enable hostapd
sudo apt -y install dnsmasq
sudo DEBIAN_FRONTEND=noninteractive apt install -y netfilter-persistent iptables-persistent
sudo echo "" >> /etc/dhcpcd.conf
sudo echo "interface wlan0" >> /etc/dhcpcd.conf
sudo echo "static ip_address=192.168.1.1/24ᅾ" >> /etc/dhcpcd.conf
sudo echo "nohook wpa_supplicant" >> /etc/dhcpcd.conf

sudo echo "net.ipv4.ip_forward=1" >> /etc/sysctl.d/routed-ap.conf
sudo iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
sudo netfilter-persistent save

sudo mv /etc/dnsmasq.conf /etc/dnsmasq.conf.orig
sudo echo "interface=wlan0 # Listening interface" >> /etc/dnsmasq.conf
sudo echo "dhcp-range=192.168.1.2,192.168.1.20,255.255.255.0,24h" >> /etc/dnsmasq.conf
sudo echo "domain=wlan     # Local wireless DNS domain" >> /etc/dnsmasq.conf
sudo echo "address=/taquillas/192.168.1.1" >> /etc/dnsmasq.conf

sudo rfkill unblock wlan

sudo echo "country_code=ES" >> /etc/hostapd/hostapd.conf
sudo echo "interface=wlan0" >> /etc/hostapd/hostapd.conf
sudo echo "ssid=WIFI_TAQUILLAS" >> /etc/hostapd/hostapd.conf
sudo echo "hw_mode=a" >> /etc/hostapd/hostapd.conf
sudo echo "channel=36" >> /etc/hostapd/hostapd.conf
sudo echo "macaddr_acl=0" >> /etc/hostapd/hostapd.conf
sudo echo "auth_algs=1" >> /etc/hostapd/hostapd.conf
sudo echo "ignore_broadcast_ssid=0" >> /etc/hostapd/hostapd.conf
sudo echo "wpa=2" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_passphrase=wifi7aqui11as" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_key_mgmt=WPA-PSK" >> /etc/hostapd/hostapd.conf
sudo echo "wpa_pairwise=TKIP" >> /etc/hostapd/hostapd.conf
sudo echo "rsn_pairwise=CCMP" >> /etc/hostapd/hostapd.conf
sudo echo "" >> /etc/hostapd/hostapd.conf

#sudo systemctl reboot

echo "Reinicia el sistema para aplicar los cambios."


