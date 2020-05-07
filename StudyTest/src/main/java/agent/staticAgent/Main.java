package agent.staticAgent;

/**
 * 功能描述: 静态代理
 *
 * @author zhangam
 * @time 2019/3/17 11:35
 * @see
 **/
public class Main {

    public static void main(String[] args) {
        //想要发达的Java3y
        Java3y java3y = new Java3y();
        //受委托程序员大V
        Programmer programmer = new ProgrammerBigV(java3y);
        //受委托程序员大V让Java3y发文章，大V(自己)来点赞
        programmer.coding();
    }

}
