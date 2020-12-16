package comprador;

import jade.core.Agent;
import behaviours.RequestPerformer;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import GUI.CompradorGUI;

public class Comprador extends Agent {
  private String bookTitle;
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private Comprador this_agent = this;
  private CompradorGUI interfaz;
  
  protected void setup() {
    System.out.println("Agente comprador " + getAID().getName() + " listo");
    interfaz=new CompradorGUI(this);
    interfaz.showGui();
  }
  
  public void comprar(String book){
      bookTitle=book;
      //if(!bookTitle.equals("")){
      interfaz.output.append("Libro: " + bookTitle+"\r\n");
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
          interfaz.output.append("\r\nIntentando comprar el libro: " + bookTitle+"\r\n");
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("book-selling");
          template.addServices(sd);
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            interfaz.output.append("Se encontraron los siguientes vendedores: \r\n");
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              interfaz.output.append(sellerAgents[i].getName()+"\r\n");
            }
            interfaz.output.append("\r\n");
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    /*} else {
      System.out.println("No se especifico el libro a comprar");
      doDelete();
    }*/
  }
  
  protected void takeDown() {
    System.out.println("Agente comprador " + getAID().getName() + " finalizado");
    interfaz.dispose();
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
  
  public CompradorGUI getInterfaz(){
      return interfaz;
  }
}
