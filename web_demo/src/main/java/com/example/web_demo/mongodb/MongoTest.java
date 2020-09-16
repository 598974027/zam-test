//package com.example.web_demo.mongodb;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 功能描述:
// *
// * @author zhangam
// * @time 2019/9/9 16:35
// * @see
// **/
//@Component
//public class MongoTest implements CommandLineRunner {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(mongoTemplate.collectionExists("TerminalRealData"));
//        Query query = new Query(Criteria.where("TERMINALCODE").is(22222));
//        Map map = (Map) mongoTemplate.findOne(query, Object.class, "TerminalRealData");
//        System.out.println(map.size());
//        Update update = new Update();
//        update.set("GDR", 100);
//        mongoTemplate.updateFirst(query, update, Object.class, "TerminalRealData");
//        map = (Map) mongoTemplate.findOne(query, Object.class, "TerminalRealData");
//        System.out.println(map.get("GDR"));
//        Map m = new HashMap<>();
//        m.put("_id", 12345678);
//        m.put("TERMINALCODE", 12345678);
//        m.put("ABC", LocalDateTime.now());
//        mongoTemplate.insert(m, "TerminalRealData");
//    }
//
//}
