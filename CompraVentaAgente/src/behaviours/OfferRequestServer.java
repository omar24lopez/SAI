package behaviours;

import vendedor.Vendedor;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class OfferRequestServer extends CyclicBehaviour{
  
  Vendedor bsAgent;
  
  public OfferRequestServer(Vendedor a) {
    bsAgent = a;
  }
  
  public void action() {
    MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
    ACLMessage msg = bsAgent.receive(mt);
    
    if(msg != null) {
      String title = msg.getContent();
      ACLMessage reply = msg.createReply();
      
      Integer price = (Integer) bsAgent.getCatalogue().get(title);
      
      if(price != null) {
        reply.setPerformative(ACLMessage.PROPOSE);
        reply.setContent(String.valueOf(price.intValue()));
      } else {
        reply.setPerformative(ACLMessage.REFUSE);
        reply.setContent("not-available");
      }
      
      bsAgent.send(reply);
    } else {
      block();
    }
  }
}
