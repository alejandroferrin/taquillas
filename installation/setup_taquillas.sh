#!/bin/bash
echo "#####################__Creando carpeta database__"
mkdir /home/pi/AppTaquillas/database_data

echo "#####################__Actualizando__"
sudo apt-get update && sudo apt-get -y upgrade

echo "#####################__Descargando App taquillas"
wget -P /home/pi/AppTaquillas/ https://github.com/alejandroferrin/taquillas/raw/main/target/mvc_taquillas-0.0.3.jar

echo "#####################__Instalando JDK__"
sudo apt -y install openjdk-8-jdk

echo "#####################__Instalando SmartCard Reader__"
sudo apt-get -y install subversion autoconf debhelper flex libusb-dev libpcsclite-dev libpcsclite1 libccid pcscd pcsc-tools libpcsc-perl libusb-1.0-0-dev libtool libssl-dev cmake checkinstall

echo "#####################__Descargando ACS driver__"
wget -P /home/pi/AppTaquillas/ https://www.acs.com.hk/download-driver-unified/11929/ACS-Unified-PKG-Lnx-118-P.zip
echo "#####################__Descomprimiendo ACS driver__"
unzip /home/pi/AppTaquillas/ACS-Unified-PKG-Lnx-118-P.zip -d AppTaquillas/
echo "#####################__Instalando ACS driver__"
sudo dpkg -i /home/pi/AppTaquillas/ACS-Unified-PKG-Lnx-118-P/raspbian/buster/libacsccid1_1.1.8-1~bpo10+1_armhf.deb

echo "#####################__Instalar pi4j__"
curl -sSL https://pi4j.com/install | sudo bash

#echo "#####################__Instalando wiringpi__"
#sudo pi4j --wiringpi

echo "#####################__Instalando wiring-pi__"
git clone https://github.com/WiringPi/WiringPi.git
sudo chmod +x /home/pi/AppTaquillas/WiringPi/build
sudo /home/pi/AppTaquillas/WiringPi/build


echo "#####################__Instalando Docker__"
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
echo "#####################__Dando permisos usuario para grupo docker__"
sudo usermod -aG docker pi
echo "#####################__Instalando docker-compose__"
sudo apt -y install docker-compose

echo "#####################__Instalando vim__"
sudo apt -y install vim
echo "#####################__Instalando vnc__"
sudo apt -y install realvnc-vnc-server realvnc-vnc-viewer

echo "#####################__Reinicio necesario__"
echo "#####################__Ahora apaga la raspberry y vuelve a encenderla__"
