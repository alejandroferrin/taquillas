/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.gpio;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
//@Component
//@Qualifier("mock")
public class GPIO_Service_Impl_Mocked implements GPIO_Service {


  @Override
  public void open(int taquillaNumber) {
    System.out.println("Taquilla Abierta nº "+taquillaNumber);
  }

  @Override
  public void close(int taquillaNumber) {
    System.out.println("Taquilla Cerrada nº "+taquillaNumber);
  }

}
