package mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/6/18 9:03
 * @see
 **/
public class MongoTest {

    public static void main(String[] args) {
        MongoUtil.init("192.168.2.110", "20000", null, null, "nek");
        MongoDatabase md = MongoUtil.getDatabase();
        md.createCollection("zam");
        MongoCollection<Document> collection = md.getCollection("test");
        MongoCursor<Document> mongoCursor = collection.find().iterator();
        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next().getObjectId("_id"));
        }
    }

}
