package protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * 功能描述: protobuf父类
 *
 * @author zhangam
 * @time 2019/3/14 11:02
 * @see
 **/
public class Base {
    @Protobuf(required = true, fieldType = FieldType.INT32, order = 1)
    public int id;
    @Protobuf(required = true, fieldType = FieldType.STRING, order = 2)
    public String token;
}
