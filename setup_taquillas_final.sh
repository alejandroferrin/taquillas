#!/bin/bash

echo "#####################__Creando servicios__"
sudo sed -i '$d' /etc/rc.local
sudo echo "sudo systemctl start pcscd" >> /etc/rc.local
sudo echo "java -jar /home/pi/AppTaquillas/mvc_taquillas-0.0.1-SNAPSHOT.jar --gpio=pi4" >> /etc/rc.local
sudo echo "exit 0" >> /etc/rc.local

sudo echo "[Desktop Entry]" >> /etc/xdg/autostart/app_taquillas.desktop
sudo echo "Name=AppTaquillas" >> /etc/xdg/autostart/app_taquillas.desktop
sudo echo "Exec=chromium-browser \"http://localhost:9000\" --kiosk" >> /etc/xdg/autostart/app_taquillas.desktop

echo "#####################__Reinicio necesario__"
echo "#####################__Ahora apaga la raspberry y vuelve a encenderla__"
