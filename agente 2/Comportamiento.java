/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author OmarLopez
 */
public class Comportamiento extends Behaviour {
    int i=0;
    public void action(){
        System.out.printf("%d\n", i);
        i++;
    }
            
    public boolean done(){
        if(i<=100)
            return false;
        else
            return true;
    }

}
