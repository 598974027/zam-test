package agent.dynamicAgent;

/**
 * 功能描述: 用户管理接口
 *
 * @author zhangam
 * @time 2019/3/17 11:33
 * @see
 **/
public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: " + userName + " password: " + password);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: " + userName);
    }

}
