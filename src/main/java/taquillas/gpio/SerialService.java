/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.gpio;

import jssc.SerialPortList;
import org.springframework.stereotype.Component;

/**
 *
 * @author alex
 */
@Component
public class SerialService {

  public String[] getSerialPorts() {
    String[] ports = SerialPortList.getPortNames();
    for (int i = 0; i < ports.length; i++) {
      System.out.println(ports[i]);
    }
    return ports;

  }
}
