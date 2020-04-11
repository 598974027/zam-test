package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Greeter extends AbstractActor {

    private ActorRef printerActor;

    public static Props props(ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(printerActor));
    }

    public Greeter(ActorRef printerActor) {
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Object.class, x -> {
            System.out.println("Greeter:" + getContext().getSystem());
            System.out.println("Greeter:" + getSender().path());
            System.out.println("Greeter:" + getSelf().path());
            System.out.println("Greeter:" + x);
            printerActor.tell(x, getSelf());
        }).build();
    }

}
