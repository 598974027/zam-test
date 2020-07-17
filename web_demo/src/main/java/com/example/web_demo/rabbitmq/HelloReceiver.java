package com.example.web_demo.rabbitmq;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/29 9:10
 * @see
 **/
//@Component
//public class HelloReceiver {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;

//    @RabbitHandler
//    @RabbitListener(queues = "zam_queue")
//    public void process(Message message, Channel channel) {
//        try {
//            String str = new String(message.getBody());
//            System.out.println("Receiver  : " + str);
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            try {
//                channel.basicRecover();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//        Message message2 = new Message("hello".getBytes(), new MessageProperties());
//        rabbitTemplate.send("test", "", message2);
//    }
//
//}
