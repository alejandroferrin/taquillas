#!/bin/bash
echo "#####################__Creando carpeta instalación__"
mkdir AppTaquillas
mkdir AppTaquillas/database_data

echo "#####################__Actualizando__"
sudo apt-get update && sudo apt-get -y upgrade

echo "#####################__Descargando App taquillas"
wget -P AppTaquillas/ https://github.com/alejandroferrin/taquillas/raw/main/target/mvc_taquillas-0.0.3.jar

echo "#####################__Instalando JDK__"
sudo apt -y install openjdk-8-jdk

echo "#####################__Instalando SmartCard Reader__"
sudo apt-get -y install subversion autoconf debhelper flex libusb-dev libpcsclite-dev libpcsclite1 libccid pcscd pcsc-tools libpcsc-perl libusb-1.0-0-dev libtool libssl-dev cmake checkinstall

echo "#####################__Descargando ACS driver__"
wget -P AppTaquillas/ https://www.acs.com.hk/download-driver-unified/11929/ACS-Unified-PKG-Lnx-118-P.zip
echo "#####################__Descomprimiendo ACS driver__"
unzip AppTaquillas/ACS-Unified-PKG-Lnx-118-P.zip -d AppTaquillas/
echo "#####################__Instalando ACS driver__"
sudo dpkg -i AppTaquillas/ACS-Unified-PKG-Lnx-118-P/raspbian/buster/libacsccid1_1.1.8-1~bpo10+1_armhf.deb

echo "#####################__Instalar pi4j__"
curl -sSL https://pi4j.com/install | sudo bash

#echo "#####################__Instalando wiringpi__"
#sudo pi4j --wiringpi

echo "#####################__Instalando wiring-pi__"
git clone https://github.com/WiringPi/WiringPi.git
cd WiringPi
sudo chmod +x build
sudo ./build

echo "#####################__Instalando Docker__"
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
echo "#####################__Dando permisos usuario para grupo docker__"
sudo usermod -aG docker pi
echo "#####################__Instalando docker-compose__"
sudo apt -y install docker-compose
echo "#####################__Configurando docker-compose.yml__"
mkdir /home/pi/database_data
echo "version: '3.1'" >> AppTaquillas/docker-compose.yml
echo "services:" >> AppTaquillas/docker-compose.yml
echo "  db:" >> AppTaquillas/docker-compose.yml
echo "    image: jsurf/rpi-mariadb:latest" >> AppTaquillas/docker-compose.yml
echo "    container_name: mariadb" >> AppTaquillas/docker-compose.yml
echo "    environment:" >> AppTaquillas/docker-compose.yml
echo "      - MYSQL_ROOT_PASSWORD=root" >> AppTaquillas/docker-compose.yml
echo "      - TZ=Europe/Madrid" >> AppTaquillas/docker-compose.yml
echo "      - MYSQL_DATABASE=mvc_taquillas" >> AppTaquillas/docker-compose.yml
echo "      - MYSQL_USER=taquillas" >> AppTaquillas/docker-compose.yml
echo "      - MYSQL_PASSWORD=7aqui11as" >> AppTaquillas/docker-compose.yml
echo "    volumes:" >> AppTaquillas/docker-compose.yml
echo "      - /home/pi/AppTaquillas/database_data:/var/lib/mysql:rw" >> AppTaquillas/docker-compose.yml
echo "    ports:" >> AppTaquillas/docker-compose.yml
echo "      - 3306:3306" >> AppTaquillas/docker-compose.yml
echo "    restart: always" >> AppTaquillas/docker-compose.yml
echo "" >> AppTaquillas/docker-compose.yml
echo "  phpmyadmin:" >> AppTaquillas/docker-compose.yml
echo "    image: phpmyadmin" >> AppTaquillas/docker-compose.yml
echo "    restart: always" >> AppTaquillas/docker-compose.yml
echo "    ports:" >> AppTaquillas/docker-compose.yml
echo "      - 8080:80" >> AppTaquillas/docker-compose.yml
echo "    environment:" >> AppTaquillas/docker-compose.yml
echo "      - PMA_ARBITRARY=1" >> AppTaquillas/docker-compose.yml

echo "#####################__Reinicio necesario__"
echo "#####################__Ahora apaga la raspberry y vuelve a encenderla__"
