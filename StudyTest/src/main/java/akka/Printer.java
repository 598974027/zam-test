package akka;

import akka.actor.AbstractActor;

public class Printer extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(Object.class, x -> {
            System.out.println("Printer:" + getContext().getSystem());
            System.out.println("Printer:" + getSender().path());
            System.out.println("Printer:" + getSelf().path());
            System.out.println("Printer:" + x);
        }).build();
    }

}
