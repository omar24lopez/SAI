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

    public void action() {
        System.out.println("Hola mundo");
    }

    public boolean done() {
        return true;
    }
}
