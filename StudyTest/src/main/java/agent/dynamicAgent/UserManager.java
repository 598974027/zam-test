package agent.dynamicAgent;

/**
 * 功能描述: 用户管理接口
 *
 * @author zhangam
 * @time 2019/3/17 11:33
 * @see
 **/
public interface UserManager {

    void addUser(String userName, String password);

    void delUser(String userName);

}
