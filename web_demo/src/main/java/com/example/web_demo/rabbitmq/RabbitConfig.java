package com.example.web_demo.rabbitmq;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/29 9:08
 * @see
 **/
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public Queue queue1() {
//        return new Queue("queue1", true, false, false);
//    }
//
//    @Bean
//    public Queue queue2() {
//        return new Queue("queue2", true, false, false);
//    }
//
//    @Bean
//    public Queue queue3() {
//        return new Queue("queue3", true, false, false);
//    }
//
//    @Bean
//    AbstractExchange exchange1() {
//        return new DirectExchange("exchange1", true, false);
//    }
//
//    @Bean
//    AbstractExchange exchange2() {
//        return new FanoutExchange("exchange2", true, false);
//    }
//
//    @Bean
//    AbstractExchange exchange3() {
//        return new HashExchange("exchange3", true, false);
//    }
//
//    @Bean
//    Binding bindingExchange1(Queue queue1, DirectExchange exchange1) {
//        return BindingBuilder.bind(queue1).to(exchange1).with("123");
//    }
//
//    @Bean
//    Binding bindingExchange2(Queue queue2, FanoutExchange exchange2) {
//        return BindingBuilder.bind(queue2).to(exchange2);
//    }
//
//    @Bean
//    Binding bindingExchange3(Queue queue3, HashExchange exchange3) {
//        return new Binding(queue3.getName(), Binding.DestinationType.QUEUE, exchange3.getName(), "10", new HashMap());
//    }
//
//}
