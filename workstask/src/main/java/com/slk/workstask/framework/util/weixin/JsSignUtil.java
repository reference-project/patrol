//package com.slk.workstask.framework.util.weixin;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Formatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import com.epoch.common.utils.JsonUtil;
///**
// * 官方给的使用js的验证工具
// * @author Administrator
// *
// */
//public class JsSignUtil {
//    public static String accessToken = null;
//    public static Map<String, Object> sign(String url) {
//        String sendUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=**appid**&secret= **secret**";
//        String result = getHttpResult(sendUrl);
//        Object accessResult = JsonUtil.getValueFromJson(result, "access_token");
//        String signUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessResult.toString()+"&type=jsapi";
//        String resultSign = getHttpResult(signUrl);
//        Object ticket = JsonUtil.getValueFromJson(resultSign, "ticket");
//        Map<String, Object> ret = new HashMap<String, Object>();
//        String nonce_str = create_nonce_str();
//        String timestamp = create_timestamp();
//        String string1;
//        String signature = "";
//        //注意这里参数名必须全部小写，且必须有序
//        string1 = "jsapi_ticket=" + ticket +
//                "&noncestr=" + nonce_str +
//                "×tamp=" + timestamp +
//                "&url=" + url;
//        try
//        {
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//            crypt.reset();
//            crypt.update(string1.getBytes("UTF-8"));
//            signature = byteToHex(crypt.digest());
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//        ret.put("url", url);
//        ret.put("nonceStr", nonce_str);
//        ret.put("timestamp", timestamp);
//        ret.put("signature", signature);
//        ret.put("jsapi_ticket", ticket);
//        ret.put("appId", "wx154f74654ae04891");
//        return ret;
//    }
//    /**
//     * 随机加密
//     * @param hash
//     * @return
//     */
//    private static String byteToHex(final byte[] hash) {
//        Formatter formatter = new Formatter();
//        for (byte b : hash)
//        {
//            formatter.format("%02x", b);
//        }
//        String result = formatter.toString();
//        formatter.close();
//        return result;
//    }
//    /*
//     * 获取访问地址链接返回值
//     */
//    private static String getHttpResult(String url) {
//        String result = "";
//        HttpGet httpRequest = new HttpGet(url);
//        try {
//            HttpResponse httpResponse = HttpClients.createDefault().execute(httpRequest);
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                result = EntityUtils.toString(httpResponse.getEntity());
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            result = e.getMessage().toString();
//        }
//        return result;
//    }
//    /**
//     * 产生随机串--由程序自己随机产生
//     * @return
//     */
//    private static String create_nonce_str() {
//        return UUID.randomUUID().toString();
//    }
//    /**
//     * 由程序自己获取当前时间
//     * @return
//     */
//    private static String create_timestamp() {
//        return Long.toString(System.currentTimeMillis() / 1000);
//    }
//}
