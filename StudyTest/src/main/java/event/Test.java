package event;

import java.util.UUID;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/2/27 19:30
 * @see
 **/
public class Test {

    public static void main(String[] args) {
        Door door = new Door();
        door.setStateListener(new DoorStateListener());
        door.setNameListener (new DoorNameListener());
        // 开门
        door.setState("open");
        System.out.println("我已经进来了");
        // 关门
        door.setState("close");
        // 改名
        door.setName("dengzy");
        System.out.println(UUID.randomUUID());
    }

}
