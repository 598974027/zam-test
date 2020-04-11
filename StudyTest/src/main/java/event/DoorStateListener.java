package event;

import java.util.EventListener;

public class DoorStateListener implements EventListener {

    public void doorEvent(DoorEvent event) {
        System.out.println(event.getKey());
        System.out.println(event.getValue());
    }

}