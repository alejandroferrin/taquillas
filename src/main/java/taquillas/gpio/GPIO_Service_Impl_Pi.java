/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author alex
 */
@Component
//@Qualifier("pi")
@ConditionalOnProperty(
  value = "gpio",
  havingValue = "pi4",
  matchIfMissing = false)

public class GPIO_Service_Impl_Pi implements GPIO_Service {

  private final List<GpioPinDigitalOutput> gpioList = new ArrayList<>();

  public GPIO_Service_Impl_Pi() {

    // create gpio controller
    final GpioController gpio = GpioFactory.getInstance();

    final GpioPinDigitalOutput pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Taquilla1", PinState.LOW);
    final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Taquilla2", PinState.LOW);
    final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Taquilla3", PinState.LOW);
    final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Taquilla4", PinState.LOW);
    final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Taquilla5", PinState.LOW);
    final GpioPinDigitalOutput pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Taquilla6", PinState.LOW);
    final GpioPinDigitalOutput pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Taquilla7", PinState.LOW);
    final GpioPinDigitalOutput pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Taquilla8", PinState.LOW);
    final GpioPinDigitalOutput pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Taquilla9", PinState.LOW);
    final GpioPinDigitalOutput pin9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Taquilla10", PinState.LOW);
    final GpioPinDigitalOutput pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "Taquilla11", PinState.LOW);
    final GpioPinDigitalOutput pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "Taquilla12", PinState.LOW);
    gpioList.add(pin0);
    gpioList.add(pin1);
    gpioList.add(pin2);
    gpioList.add(pin3);
    gpioList.add(pin4);
    gpioList.add(pin5);
    gpioList.add(pin6);
    gpioList.add(pin7);
    gpioList.add(pin8);
    gpioList.add(pin9);
    gpioList.add(pin10);
    gpioList.add(pin11);

  }

  @Override
  public void open(int taquillaNumber) {
    System.out.println("Pi_ Taquilla Abierta nº: " + taquillaNumber);
    gpioList.get(taquillaNumber - 1).high();

  }

  @Override
  public void close(int taquillaNumber) {
    System.out.println("Pi_ Taquilla Cerrada nº: " + taquillaNumber);
    gpioList.get(taquillaNumber - 1).low();

  }

}
