package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaQuickstart {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("helloakka");
        try {
            //create-actors
            final ActorRef printerActor = system.actorOf(Props.create(Printer.class), "printerActor");
            final ActorRef greeterActor = system.actorOf(Greeter.props(printerActor), "greeterActor");
            //send-messages
            greeterActor.tell("123456789", ActorRef.noSender());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
