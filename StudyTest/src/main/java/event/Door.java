package event;

import event.DoorStateListener;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/2/27 19:28
 * @see
 **/
public class Door {

    private String state = "";
    private String name = "";
    private DoorStateListener stateListener;
    private DoorNameListener nameListener;

    public void setState(String newValue) {
        if (state != newValue) {
            state = newValue;
            if (stateListener != null){
                //注意参数传递
                DoorEvent event = new DoorEvent(this, "state", newValue);
                stateListener.doorEvent(event);
            }
        }
    }

    public void setName(String newValue) {
        if (name != newValue) {
            name = newValue;
            if (nameListener != null){
                DoorEvent event = new DoorEvent(this,"name", newValue);
                nameListener.doorEvent(event);
            }
        }
    }

    public void setStateListener(DoorStateListener stateListener) {
        this.stateListener = stateListener;
    }

    public void setNameListener(DoorNameListener nameListener) {
        this.nameListener = nameListener;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}