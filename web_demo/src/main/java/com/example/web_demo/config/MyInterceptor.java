//package com.example.web_demo.config;
//
///**
// * 功能描述: 拦截器
// *
// * @author zhangam
// * @time 2019/5/26 13:41
// * @see
// **/
//@Component
//public class MyInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        System.out.println("拦截器逻辑操作。。。" + request.getRequestURL().toString());
//        //如果不是映射到方法直接通过
//        if (!(object instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                String token = request.getHeader("token");
//                //执行认证
//                if (token == null) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//                //获取token中的userId
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                } catch (JWTDecodeException j) {
//                    throw new RuntimeException("401");
//                }
//                User user = new User("1", "admin", "123456");
//                if (user == null) {
//                    throw new RuntimeException("用户不存在，请重新登录");
//                }
//                //验证token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
//                }
//                return true;
//            }
//        }
//        return true;
//    }
//
//}
