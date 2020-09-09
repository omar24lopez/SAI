/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente;
import jade.core.Agent;
/**
 *
 * @author OmarLopez
 */
public class Agente extends Agent {
    Comportamiento c = new Comportamiento();
    
    protected void setup(){
        this.addBehaviour(c);
    }
    
}
