package stream;

import java.io.Serializable;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/5 14:55
 * @see
 **/
class User implements Serializable {//必须实现Serializable接口
    public String uid;
    public String pwd;

    public User(String _uid, String _pwd) {
        this.uid = _uid;
        this.pwd = _pwd;
    }

    @Override
    public String toString() {
        return "账号:" + this.uid + " 密码:" + this.pwd;
    }
}