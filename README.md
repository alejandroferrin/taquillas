# App Taquillas
La aplicación taquillas sirve para gestionar un taquillero que guarde material con acceso restringido.

Esta aplicación ha sido creada para su uso en una fábrica en la que se quería permitir el acceso a ciertos EPI's solo a parte de la plantilla.

La aplicación permite crear usuarios asociados a un número de tarjeta nfc que tendrán acceso solo a las taquillas autorizadas.

[Demo](https://alejandroferrin.github.io/taquillas/) 

## Material necesario
La aplicación está pensada para activar los GPIO de una __Raspberry Pi 4__ o una __Raspberry Pi Pico__

Si no se puede correr sobre una __Raspberry Pi 4__ porque la política de la empresa en la que se instala el taquillero obliga a usar un Sistema Operativo Windows se puede ejecutar el __JAR__ en una máquina con Windows y con una __Raspberry Pi Pico__ conectada mediante usb al servidor accionar los relés.



__Material para 12 taquillas__

![fotos_material](images/material.png)

_(Los links son SIN enlace de afiliado)_

- __A__ [Raspberry Pi 4 4GB](https://es.rs-online.com/web/p/raspberry-pi/1822096) (x1)

- __B__ [Relé 16 canales 5V](https://es.aliexpress.com/item/4000222568835.html?spm=a2g0o.productlist.0.0.335c6cf4dRP2Dj&algo_pvid=2d5e2ea5-c720-474f-b1a5-0c4bc8ff43b3&algo_exp_id=2d5e2ea5-c720-474f-b1a5-0c4bc8ff43b3-0) (x1)

- __C__ [Pantalla táctil 10"](https://www.amazon.es/port%C3%A1til-Kenowa-Seguridad-autom%C3%B3vil-Raspberry/dp/B07Z3KQ7W3/ref=sr_1_23?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=monitor%2Btactil&qid=1630531056&sr=8-23&th=1) (x1)

- __D__ [Carrete cable 2 x 0,4 (100m)](https://es.rs-online.com/web/p/cables-para-altavozes/7140316/) (x1)

- __E__ [Terminal Carril DIN](https://es.rs-online.com/web/p/bloques-terminales-de-carril-din/0424759/) (x30)

- __F__ [Lector tarjetas NFC](https://www.amazon.es/Escritor-inteligente-grabador-tarjetas-inteligentes/dp/B07X55CL4S/ref=sr_1_38?dchild=1&keywords=ACR122U&qid=1629795588&refinements=p_36%3A-4200&rnid=1323854031&sr=8-38)  (x1)

Lector tarjetas NFC ACS - [ACR122U](https://www.acs.com.hk/en/products/3/acr122u-usb-nfc-reader/) 

- __G__ [Fuente alimentación 5V DIN](https://es.rs-online.com/web/p/fuentes-de-alimentacion-de-montaje-en-carril-din/1368304) (x1)

- __H__ [Cable HDMI a micro HDMI](https://es.rs-online.com/web/p/cables-para-raspberry-pi/1871377/) (x1)

- __I__ [Cerradura eléctrica 5V](https://es.aliexpress.com/item/1005003038262076.html?spm=a2g0o.cart.0.0.556f3c00RlUl5S&mp=1)  (x12)

- __J__ [Ventilador 220v 9225 Dual Ball](https://es.aliexpress.com/item/1005001775087852.html?spm=a2g0o.detail.100009.3.7893287ec6pY6y&amp;gps-id=pcDetailLeftTopSell&amp;scm=1007.13482.95643.0&amp;scm_id=1007.13482.95643.0&amp;scm-url=1007.13482.95643.0&amp;pvid=79ae7cf3-9e93-4aed-b17e-f6d4ae730271&amp;_t=gps-id:pcDetailLeftTopSell,scm-url:1007.13482.95643.0,pvid:79ae7cf3-9e93-4aed-b17e-f6d4ae730271,tpp_buckets:668%230%23131923%2327_668%230%23131923%2327_668%23888%233325%231_668%23888%233325%231_668%232846%238114%231999_668%235811%2327178%2332_668%237206%2334833%2320_668%236421%2330822%23148_668%232717%237564%23620_668%231000022185%231000066058%230_668%236808%2332770%23187_668%233480%2315683%23664_668%232846%238114%231999_668%235811%2327178%2332_668%237206%2334833%2320_668%236421%2330822%23148_668%232717%237564%23620_668%233164%239976%23866_668%236808%2332770%23187_668%233480%2315683%23664&amp;&amp;pdp_ext_f=%7B%22sceneId%22:%223482%22%7D) (x1)

- __K__ [Cable jumper female-female](https://es.aliexpress.com/item/4000204863216.html?spm=a2g0o.productlist.0.0.43a05fdaCym1zF&algo_pvid=84528e71-9fde-401f-9ca0-051ac59dd1d5&algo_exp_id=84528e71-9fde-401f-9ca0-051ac59dd1d5-1) (x1)

- __L__ [Enchufe montaje DIN](https://es.aliexpress.com/item/1005002837461759.html?srcSns=sns_Copy&amp;spreadType=socialShare&amp;bizType=ProductDetail&amp;social_params=60093403662&amp;aff_fcid=373826c1a8214d61bb242367fd4e4d70-1630904138203-05809-_vPJppU&amp;tt=MG&amp;aff_fsk=_vPJppU&amp;aff_platform=default&amp;sk=_vPJppU&amp;aff_trace_key=373826c1a8214d61bb242367fd4e4d70-1630904138203-05809-_vPJppU&amp;shareId=60093403662&amp;businessType=ProductDetail&amp;platform=AE&amp;terminal_id=434d7d9f216e4811937444fc960d5c93) 

- __M__ [Toma alimentación](https://es.rs-online.com/web/p/conectores-iec/8117210/) (x1)

- __N__ [Tarjeta micro SD 32GB](https://es.rs-online.com/web/p/tarjetas-micro-sd/2034765) (x1)

- __O__ [Cable alimentación 5m](https://es.rs-online.com/web/p/cables-de-alimentacion-para-equipos/6151154) (x1)
- __P__ [Jumpers p/bloque terminal](https://es.rs-online.com/web/p/accesorios-para-terminal-de-carril-din/8787581/?origin=PSF_435779|alt) (x5)

- __Q__ [Carril DIN 500 mm](https://es.rs-online.com/web/p/carriles-din/0467406/) (x1)

- __R__ [Soporte PCB carril DIN](https://es.aliexpress.com/item/32627565613.html?srcSns=sns_Copy&amp;spreadType=socialShare&amp;bizType=ProductDetail&amp;social_params=60093403678&amp;aff_fcid=f5e39ac6299440439a6f9ff8aca7a0ca-1630904146078-09924-_v3YYwS&amp;tt=MG&amp;aff_fsk=_v3YYwS&amp;aff_platform=default&amp;sk=_v3YYwS&amp;aff_trace_key=f5e39ac6299440439a6f9ff8aca7a0ca-1630904146078-09924-_v3YYwS&amp;shareId=60093403678&amp;businessType=ProductDetail&amp;platform=AE&amp;terminal_id=434d7d9f216e4811937444fc960d5c93) 

- __S__ [Caja 400x300x150](https://es.rs-online.com/web/p/cajas-de-pared/7755798) (x1)

- __T__ [Carcasa Raspberry Pi 4 carril DIN](https://es.aliexpress.com/item/1005002226947544.html?srcSns=sns_Copy&amp;spreadType=socialShare&amp;bizType=ProductDetail&amp;social_params=60092905860&amp;aff_fcid=217b53087a5a48daa9a6578516613a54-1630904144180-01400-_v0pm8q&amp;tt=MG&amp;aff_fsk=_v0pm8q&amp;aff_platform=default&amp;sk=_v0pm8q&amp;aff_trace_key=217b53087a5a48daa9a6578516613a54-1630904144180-01400-_v0pm8q&amp;shareId=60092905860&amp;businessType=ProductDetail&amp;platform=AE&amp;terminal_id=434d7d9f216e4811937444fc960d5c93)  (x1)

- __U__ [Disipadores calor Rpi 4](https://es.rs-online.com/web/p/placas-hat-y-complementos-para-raspberry-pi/2020460) (x1)

- __V__ [Placa terminación](https://es.rs-online.com/web/p/accesorios-para-terminal-de-carril-din/8787540/)  (x10)

- [Taquillas](https://www.amazon.es/compartimento-taquilla-Compartimiento-revestimiento-Antracita/dp/B07BZKZ557/ref=sr_1_32?__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=taquillas+12&qid=1630611007&sr=8-32)

----
## Esquema

El sistema permite cablear hasta 20 taquilas, de la salida 0 a la 19. 

(Ejemplo para 2 taquillas)

![cableado](images/wiring.png)


### Importante -> 
__En Raspberry Pi 4 se debe usar el diagrama de GPIO de [Pi4J](https://pi4j.com/1.3/pins/rpi-4b.html)__

![gpio](images/pi4j-rpi-4b-pinout-small.png)

![esquema](images/esquema.png)

![esquema eléctrico](images/esquema-electrico.png)
----
## Instalación

### Raspberry Pi

Instala en una tarjeta mirco SD __Raspberry Pi OS__ usando [Raspberry Pi Imager](https://www.raspberrypi.org/software/) 

Asigna una IP fija a la Raspberry [tutorial](https://raspberryparanovatos.com/tutoriales/asignar-ip-fija-raspberry-pi/) 

Comando instalación:

`curl -s https://raw.githubusercontent.com/alejandroferrin/taquillas/main/install | sudo bash`

Tras la ejecución del script debes apagar y volver a encender la Raspberry y después ejecutar el siguiente comando:

`sudo /home/pi/AppTaquillas/setup_taquillas_final.sh`

__Tareas que realizan el script__

- Crear carpeta AppTaquillas
- Actualizar sistema
- Instalar JDK 1.8
- Instalar librerías funcionamiento smart card reader
- Instalar drivers ACS
- Instalar pi4j
- Instalar WiringPi
- Instalar docker
- Dar permisos al usuario en grupo docker
- Instalar docker-compose
- Crear el archivo docker-compose.yml
- Levantar contenedores docker
- Ejecutar la aplicación al encender el sistema

En el proceso se instala un administrador de bases de datos al cual podrás acceder a través del navegador en el puerto _8080_

Debes volver a reiniciar el equipo para que la aplicación detecte la base de datos al ejecutarse.

Para cambiar los argumentos de la ejecución del servicio puedes modificar  el archivo __taquillas__

Por ejemplo para la ejecución con cambio de contraseña deberías añadir:

`--admin_password=tu_pass`

### Windows

Actualmente no existe un script que automatice la instalación en windows no obstante los pasos a seguir son los siguientes:


- Instalar JDK 1.8
- (En Windows 10 no es necesario instalar drivers para el lector de tarjetas)
- Instalar mariadb y [crear una base de datos](https://www.daniloaz.com/es/como-crear-un-usuario-en-mysql-mariadb-y-concederle-permisos-para-una-base-de-datos-desde-la-linea-de-comandos/)  llamada mvc_taquillas con un usuario llamado _taquillas_ y password _7aqui11as_
- Descargar [ejecutable](https://github.com/alejandroferrin/taquillas/raw/main/target/mvc_taquillas-0.0.1-SNAPSHOT.jar) 
- Crear servicio que levante al arranque la app:

Deberá ejecutar algo como:
`java -jar <ruta_al_ejecutable> --admin_password=mi_password`

- Cargar el archivo [main.py](pico/main.py) en la __Raspberry Pi Pico__ con el IDE [Thonny](https://thonny.org/) (Se debe configurar la __Pico__ para [usar micropython](https://www.raspberrypi.org/documentation/rp2040/getting-started/#getting-started-with-micropython) )


----
## Uso

[Demo](https://alejandroferrin.github.io/taquillas/) 

La aplicación se ejecuta en el __puerto 9000__ por lo que para acceder se debe escribir en la barra de direcciones de un navegador `http://ip_maquina_servidor:9000`

La pantalla principal se ha creado con la intención de que se visualice en una pantalla táctil próxima al taquillero.

En esta pantalla el usuario debe presionar el botón con el icono de la tarjeta y a continuación se le pedirá que acerque la tarjeta para ser leída. Una vez identificado el usuario podrá seleccionar el artículo deseado y la cantidad a retirar. Si el usuario cuenta con los permisos para la taquilla que contiene los artículos esta se abrirá y podrán ser retirados.

#### Zona del administrador del taquillero
Presionando el botón de Admin accedemos a la zona de administración, en la que se pueden crear usuarios, items, taquillas y consultar las retiradas de material.

El usuario para acceder a esta zona es _admin_ y el password por defecto _7aqui11as_.

El password puede cambiarse si al ejecutar el programa lo hacemos con el argumento `--admin_password=nuevo_password`

- Ejemplo:

`java -jar mvc_taquillas-0.0.3.jar --admin_password=1234`


### Argumentos

- `--linuxport=xxx`
- `--winPort=yyy`
- `--admin_password=my_password`
- `--gpio=pico` o `--gpio=pi4`

### Extra

[Configurar la Raspberry como Access Point](https://github.com/alejandroferrin/taquillas/tree/main/installation/utils) 



