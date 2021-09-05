#!/bin/bash
echo "#####################__Actualizando__"
sudo apt-get update && sudo apt-get -y upgrade
echo "#####################__Instalando vim__"
sudo apt -y install vim
echo "#####################__Instalando vnc__"
sudo apt -y install realvnc-vnc-server realvnc-vnc-viewer

