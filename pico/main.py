import machine
import utime
import time
import sys
import select

led_on = machine.Pin(25, machine.Pin.OUT)
led_on.value(0)

s0 = machine.Pin(0, machine.Pin.OUT)
s1 = machine.Pin(1, machine.Pin.OUT)
s2 = machine.Pin(2, machine.Pin.OUT)
s3 = machine.Pin(3, machine.Pin.OUT)
s4 = machine.Pin(4, machine.Pin.OUT)
s5 = machine.Pin(5, machine.Pin.OUT)
s6 = machine.Pin(6, machine.Pin.OUT)
s7 = machine.Pin(7, machine.Pin.OUT)
s8 = machine.Pin(8, machine.Pin.OUT)
s9 = machine.Pin(9, machine.Pin.OUT)
s10 = machine.Pin(10, machine.Pin.OUT)
s11 = machine.Pin(11, machine.Pin.OUT)
s12 = machine.Pin(12, machine.Pin.OUT)
s13 = machine.Pin(13, machine.Pin.OUT)
s14 = machine.Pin(14, machine.Pin.OUT)
s15 = machine.Pin(15, machine.Pin.OUT)
s16 = machine.Pin(16, machine.Pin.OUT)
s17 = machine.Pin(17, machine.Pin.OUT)
s18 = machine.Pin(18, machine.Pin.OUT)
s19 = machine.Pin(19, machine.Pin.OUT)
s20 = machine.Pin(20, machine.Pin.OUT)
s21 = machine.Pin(21, machine.Pin.OUT)
s22 = machine.Pin(22, machine.Pin.OUT)


poll = select.poll()
poll.register( sys.stdin, select.POLLIN )
print("running...")

while True:    
    res = poll.poll()        
    ch = res[0][0].read(3)

    if (ch == '00o'):
        s0.value(1)            
        utime.sleep(0.5)
        s0.value(0)            
        
    if (ch == '01o'):
        s1.value(1)
        utime.sleep(0.5)
        s1.value(0)            

    if (ch == '02o'):
        s2.value(1)
        utime.sleep(0.5)
        s2.value(0)            

    if (ch == '03o'):
        s3.value(1)
        utime.sleep(0.5)
        s3.value(0)            

    if (ch == '04o'):
        s4.value(1)
        utime.sleep(0.5)
        s4.value(0)            

    if (ch == '05o'):
        s5.value(1)
        utime.sleep(0.5)
        s5.value(0)            

    if (ch == '06o'):
        s6.value(1)
        utime.sleep(0.5)
        s6.value(0)            

    if (ch == '07o'):
        s7.value(1)
        utime.sleep(0.5)
        s7.value(0)            

    if (ch == '08o'):
        s8.value(1)
        utime.sleep(0.5)
        s8.value(0)            

    if (ch == '09o'):
        s9.value(1)
        utime.sleep(0.5)
        s9.value(0)            

    if (ch == '10o'):
        s10.value(1)
        utime.sleep(0.5)
        s10.value(0)            

    if (ch == '11o'):
        s11.value(1)
        utime.sleep(0.5)
        s11.value(0)            

    if (ch == '12o'):
        s12.value(1)
        utime.sleep(0.5)
        s12.value(0)            

    if (ch == '13o'):
        s13.value(1)
        utime.sleep(0.5)
        s13.value(0)            

    if (ch == '14o'):
        s14.value(1)
        utime.sleep(0.5)
        s14.value(0)            

    if (ch == '15o'):
        s15.value(1)
        utime.sleep(0.5)
        s15.value(0)            

    if (ch == '16o'):
        s16.value(1)
        utime.sleep(0.5)
        s16.value(0)            

    if (ch == '17o'):
        s17.value(1)
        utime.sleep(0.5)
        s17.value(0)            

    if (ch == '18o'):
        s18.value(1)
        utime.sleep(0.5)
        s18.value(0)            

    if (ch == '19o'):
        s19.value(1)
        utime.sleep(0.5)
        s19.value(0)            

    if (ch == '20o'):
        s20.value(1)
        utime.sleep(0.5)
        s20.value(0)            

    if (ch == '21o'):
        s21.value(1)
        utime.sleep(0.5)
        s21.value(0)            

    if (ch == '22o'):
        s22.value(1)
        utime.sleep(0.5)
        s22.value(0)            

            
