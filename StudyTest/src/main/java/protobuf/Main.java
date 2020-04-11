package protobuf;

import com.google.protobuf.ExtensionRegistry;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/3/14 11:06
 * @see
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        //父类赋值
        BaseBuf.Base.Builder base = BaseBuf.Base.newBuilder().setId(1).setToken("token");
        //子类赋值
        ExtendBuf.Extend.Builder extend = ExtendBuf.Extend.newBuilder().setName("name").setSex(2);
        //父子类Extension
        base.setExtension(ExtendBuf.Extend.extend, extend.build());
        //序列化
        byte[] b = base.build().toByteArray();

        //ExtensionRegistry实例
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        //子类注册Extension
        ExtendBuf.registerAllExtensions(registry);
        //反序列化
        BaseBuf.Base ent = BaseBuf.Base.parseFrom(b, registry);
        System.out.println(ent.getId());
        System.out.println(ent.getToken());
        System.out.println(ent.getExtension(ExtendBuf.Extend.extend).getName());
        System.out.println(ent.getExtension(ExtendBuf.Extend.extend).getSex());
    }

}
