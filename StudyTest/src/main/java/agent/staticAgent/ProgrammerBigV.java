package agent.staticAgent;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/3/17 11:34
 * @see
 **/
public class ProgrammerBigV implements Programmer {

    // 指定程序员大V要让谁发文章(先发文章、后点赞)
    private Java3y java3y;

    public ProgrammerBigV(Java3y java3y) {
        this.java3y = java3y;
    }

    // 程序员大V点赞评论收藏转发
    public void upvote() {
        System.out.println("程序员大V点赞评论收藏转发！");
    }

    @Override
    public void coding() {

        // 让Java3y发文章
        java3y.coding();

        // 程序员大V点赞评论收藏转发！
        upvote();
    }

}
