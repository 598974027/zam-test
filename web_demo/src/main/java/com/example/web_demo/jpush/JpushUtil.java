package com.example.web_demo.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.DeviceType;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 功能描述: JpushUtils
 *
 * @author zhangaomin
 * @time 2021/5/14 11:19
 **/
@Component
public class JpushUtil {
    private final Logger logger = LoggerFactory.getLogger(JpushUtil.class);

    @Resource
    private JpushConfig jpushConfig;

    /**
     * 查询用户
     *
     * @param alias 用户别名
     */
    public AliasDeviceListResult getUser(String alias) throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        AliasDeviceListResult result = jpushClient.getAliasDeviceList(alias, DeviceType.Android.value());
        return result;
    }

    /**
     * 发送通知消息
     *
     * @param title     App通知栏标题
     * @param content   App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap 额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias     别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     */
    public PushResult sendPush(String title, String content, Map<String, String> extrasMap, String... alias) throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
        PushResult result = jpushClient.sendPush(payload);
        return result;
    }

    /**
     * 发送自定义消息，由APP端拦截信息后再决定是否创建通知
     *
     * @param title     App通知栏标题
     * @param content   App通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap 额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias     别名数组，设定哪些用户手机能接收信息（为空则所有用户都推送）
     * @return PushResult
     */
    public PushResult sendCustomPush(String title, String content, Map<String, String> extrasMap, String... alias) throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.parseLong(jpushConfig.getLiveTime()));
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey(), null, clientConfig);
        PushPayload payload = buildCustomPushPayload(title, content, extrasMap, alias);
        PushResult result = jpushClient.sendPush(payload);
        return result;
    }

    /**
     * 异步请求推送方式
     * 使用NettyHttpClient,异步接口发送请求，通过回调函数可以获取推送成功与否情况
     *
     * @param title     通知栏标题
     * @param content   通知栏内容（为了单行显示全，尽量保持在22个汉字以下）
     * @param extrasMap 额外推送信息（不会显示在通知栏，传递数据用）
     * @param alias     需接收的用户别名数组（为空则所有用户都推送）
     */
    public void sendPushWithCallback(String title, String content, Map<String, String> extrasMap, String... alias) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(Long.valueOf(jpushConfig.getLiveTime()));
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(jpushConfig.getAppkey(), jpushConfig.getMasterSecret()), null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushPayload(title, content, extrasMap, alias);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    if (200 == responseWrapper.responseCode) {
                        logger.info("极光推送成功，返回结果: " + responseWrapper.responseContent);
                    } else {
                        logger.info("极光推送失败，返回结果: " + responseWrapper.responseContent);
                    }
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    /**
     * 设置、更新、设备的 tag, alias 信息
     *
     * @param registrationId 设备的registrationId
     * @param alias          更新设备的别名属性
     * @param tagsToAdd      添加设备的tag属性
     * @param tagsToRemove   移除设备的tag属性
     */
    private void updateDeviceTagAlias(String registrationId, String alias, Set<String> tagsToAdd, Set<String> tagsToRemove) throws APIConnectionException, APIRequestException {
        JPushClient jpushClient = new JPushClient(jpushConfig.getMasterSecret(), jpushConfig.getAppkey());
        jpushClient.updateDeviceTagAlias(registrationId, alias, tagsToAdd, tagsToRemove);
    }

    /**
     * 根据标签推送相应的消息
     *
     * @param title     推送消息标题
     * @param content   推送消息内容
     * @param extrasMap 推送额外信息
     * @param alias     推送的目标别名
     * @return
     */
    private PushPayload buildPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap();
        }
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder()
                //设置推送平台
                .setPlatform(Platform.android_ios())
                //设置标签
                .setAudience(Audience.alias(newAlias))
                //设置通知
                .setNotification(Notification.newBuilder().setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build()).build())
                //设置选项
//            .setOptions(Options.newBuilder().setApnsProduction(false).setTimeToLive(8600).setBigPushDuration(1).build())
                //设置通知内容
//            .setMessage(Message.newBuilder().setTitle("").setMsgContent("").setContentType("").build())
                .build();
    }

    /**
     * 根据标签推送相应的消息
     *
     * @param title     推送消息标题
     * @param content   推送消息内容
     * @param extrasMap 推送额外信息
     * @param alias     推送的目标别名
     * @return
     */
    private PushPayload buildCustomPushPayload(String title, String content, Map<String, String> extrasMap, String... alias) {
        if (extrasMap == null || extrasMap.isEmpty()) {
            extrasMap = new HashMap();
        }
        String[] newAlias = removeArrayEmptyElement(alias);
        return PushPayload.newBuilder().setPlatform(Platform.android_ios())
                .setAudience((null == newAlias || newAlias.length == 0) ? Audience.all() : Audience.alias(newAlias))
                .setNotification(Notification.newBuilder().setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extrasMap).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtras(extrasMap).build()).build())
                .setMessage(Message.newBuilder().setTitle(title).setMsgContent(content).addExtras(extrasMap).build())
                .build();
    }

    /**
     * 删除别名中的空元素（需删除如：null,""," "）
     *
     * @param strArray
     * @return String[]
     */
    private String[] removeArrayEmptyElement(String... strArray) {
        if (null == strArray || strArray.length == 0) {
            return null;
        }
        List<String> tempList = Arrays.asList(strArray);
        List<String> strList = new ArrayList<String>();
        Iterator<String> iterator = tempList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (null != str && !"".equals(str.trim())) {
                strList.add(str);
            }
        }
        String[] newStrArray = strList.toArray(new String[strList.size()]);
        return newStrArray;
    }
}
