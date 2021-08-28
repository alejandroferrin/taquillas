#!/bin/bash

echo "#####################__Docker-Compose up__"
cd /home/pi/AppTaquillas
docker-compose up -d


echo "#####################__Creando servicios__"
sudo sed -i '$d' /etc/rc.local
sudo echo "sudo systemctl start pcscd" >> /etc/rc.local
sudo echo "exit 0" >> /etc/rc.local

sudo cp /home/pi/AppTaquillas/app_taquillas.desktop /etc/xdg/autostart/
sudo cp /home/pi/AppTaquillas/taquillas.service /etc/systemd/system/
sudo chmod +x /home/pi/AppTaquillas/taquillas
sudo systemctl daemon-reload
sudo systemctl enable taquillas.service

echo "#####################__Reinicio necesario__"
echo "#####################__Ahora apaga la raspberry y vuelve a encenderla__"
