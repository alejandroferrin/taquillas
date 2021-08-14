/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquillas.gpio;

/**
 *
 * @author alex
 */
public interface GPIO_Service {

  public void open(int taquillaNumber);

  public void close(int taquillaNumber);
}
