/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.gpio;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import static jssc.SerialPort.BAUDRATE_115200;
import static jssc.SerialPort.DATABITS_8;
import static jssc.SerialPort.PARITY_NONE;
import static jssc.SerialPort.STOPBITS_1;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
@Component
//@Qualifier("pico")
@ConditionalOnProperty(
  value = "gpio",
  havingValue = "pico",
  matchIfMissing = true)
public class GPIO_Service_Impl_Pico implements GPIO_Service {

  @Value("${linuxPort}")
  private String linuxPort;
  @Value("${winPort}")
  private String winPort;

  private SerialPort init() {
    SerialPort port = null;
    String os = System.getProperty("os.name");
    if (isWindows(os)) {
      port = new SerialPort(winPort);

    } else if (isLinux(os)) {
      port = new SerialPort(linuxPort);

    }
    return port;
  }

  @Override
  public void open(int taquillaNumber) {
    try {
      SerialPort port = init();
      System.out.println("Pico_ Taquilla Abierta nº: " + taquillaNumber);
      port.openPort();
      port.setParams(BAUDRATE_115200, DATABITS_8, STOPBITS_1, PARITY_NONE);
      port.writeBytes(numberToString(taquillaNumber, 'o').getBytes());
      port.closePort();
    } catch (SerialPortException ex) {
      Logger.getLogger(GPIO_Service_Impl_Pico.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void close(int taquillaNumber) {
    try {
      SerialPort port = init();
      System.out.println("Pico_ Taquilla Cerrada nº: " + taquillaNumber);
      port.openPort();
      port.setParams(BAUDRATE_115200, DATABITS_8, STOPBITS_1, PARITY_NONE);
      port.writeBytes(numberToString(taquillaNumber, 'c').getBytes());
      port.closePort();
    } catch (SerialPortException ex) {
      Logger.getLogger(GPIO_Service_Impl_Pico.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private boolean isWindows(String os) {
    return (os.toLowerCase().contains("win"));
  }

  private boolean isMac(String os) {
    return (os.toLowerCase().contains("mac"));
  }

  private boolean isLinux(String os) {
    return (os.toLowerCase().contains("linux"));
  }

  private String numberToString(Integer number, char type) {
    number--;
    String sNumber = number.toString();
    if (sNumber.length() < 2) {
      sNumber = 0 + sNumber;
    }
    sNumber += type;
    return sNumber;
  }

}
