package netty.tcp1;

import com.intest.base.util.DateUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * 功能描述: 服务端 客户端 SSLContext
 *
 * @author zhangam
 * @time 2019/10/15 13:58
 * @see
 **/
public class MySSLContext {

    public static String caSerialNumber = null;

    /**
     * 服务端SSLContext
     *
     * @return
     */
    public static SSLContext getSSLContext() {
        SSLContext serverContext = null;
        try {
            //保存服务器端的私钥
            KeyStore ks = KeyStore.getInstance("JKS");
            InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/server-keystore.jks"));
            ks.load(in, "server".toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, "server".toCharArray());
            in.close();
            Enumeration<String> e = ks.aliases();
            while (e.hasMoreElements()) {
                String alias = e.nextElement();
                X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
                System.out.println("服务端证书别名:" + alias);
                System.out.println("服务端证书序列号:" + certificate.getSerialNumber().toString());
//                System.out.println("服务端证书公钥:" + Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded()));
//                PrivateKey privateKey = (PrivateKey) ks.getKey(alias, "server".toCharArray());
//                if (privateKey != null) {
//                    System.out.println("服务端证书私钥:" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//                }
                System.out.println("开始时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotBefore()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
                System.out.println("失效时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotAfter()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
            }
            //保存客户端的授权证书
            KeyStore ks2 = KeyStore.getInstance("JKS");
            InputStream in2 = new FileInputStream(new File(System.getProperty("user.dir") + "/server-truststore.jks"));
            ks2.load(in2, "server".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks2);
            in2.close();
            Enumeration<String> e2 = ks2.aliases();
            while (e2.hasMoreElements()) {
                String alias = e2.nextElement();
                X509Certificate certificate = (X509Certificate) ks2.getCertificate(alias);
                System.out.println("授权证书别名:" + alias);
                caSerialNumber = certificate.getSerialNumber().toString();
                System.out.println("授权证书序列号:" + certificate.getSerialNumber().toString());
//                System.out.println("授权证书公钥:" + Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded()));
//                PrivateKey privateKey = (PrivateKey) ks2.getKey(alias, "server".toCharArray());
//                if (privateKey != null) {
//                    System.out.println("授权证书私钥:" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//                }
                System.out.println("开始时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotBefore()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
                System.out.println("失效时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotAfter()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
            }
            serverContext = SSLContext.getInstance("TLSv1.2");
            //双向
            serverContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverContext;
    }

    /**
     * 客户端SSLContext
     *
     * @return
     */
    public static SSLContext getSSLContext2() {
        SSLContext clientContext = null;
        try {
            //保存客户端的私钥
            KeyStore ks = KeyStore.getInstance("JKS");
            InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/client-keystore2.jks"));
            ks.load(in, "client".toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, "client".toCharArray());
            in.close();
            Enumeration<String> e = ks.aliases();
            while (e.hasMoreElements()) {
                String alias = e.nextElement();
                X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
                System.out.println("客户端证书别名:" + alias);
                System.out.println("客户端证书序列号:" + certificate.getSerialNumber().toString());
//                System.out.println("客户端证书公钥:" + Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded()));
//                PrivateKey privateKey = (PrivateKey) ks.getKey(alias, "client".toCharArray());
//                if (privateKey != null) {
//                    System.out.println("客户端证书私钥:" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//                }
                System.out.println("开始时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotBefore()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
                System.out.println("失效时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotAfter()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
            }
            //保存服务端的授权证书
            KeyStore ks2 = KeyStore.getInstance("JKS");
            InputStream in2 = new FileInputStream(new File(System.getProperty("user.dir") + "/client-truststore2.jks"));
            ks2.load(in2, "client".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks2);
            in2.close();
            Enumeration<String> e2 = ks2.aliases();
            while (e2.hasMoreElements()) {
                String alias = e2.nextElement();
                X509Certificate certificate = (X509Certificate) ks2.getCertificate(alias);
                System.out.println("授权证书别名:" + alias);
                System.out.println("授权证书序列号:" + certificate.getSerialNumber().toString());
//                System.out.println("授权证书公钥:" + Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded()));
//                PrivateKey privateKey = (PrivateKey) ks2.getKey(alias, "client".toCharArray());
//                if (privateKey != null) {
//                    System.out.println("授权证书私钥:" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//                }
                System.out.println("开始时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotBefore()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
                System.out.println("失效时间：" + DateUtil.formatLocalDateTime(DateUtil.dateConvertToLocalDateTime(certificate.getNotAfter()), DateUtil.DATETIME_FORMATTER_WITH_MILL));
            }
            clientContext = SSLContext.getInstance("TLSv1.2");
            //双向
            clientContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientContext;
    }

    /**
     * 客户端SSLContext
     *
     * @return
     */
    public static SSLContext getSSLContext3() {
        SSLContext clientContext = null;
        try {
            //保存客户端的私钥
            KeyStore ks = KeyStore.getInstance("JKS");
            InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/n60-keystore.jks"));
            ks.load(in, "server".toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, "1qaz@WSX".toCharArray());
            in.close();
            Enumeration<String> e = ks.aliases();
            //保存服务端的授权证书
            KeyStore ks2 = KeyStore.getInstance("JKS");
            InputStream in2 = new FileInputStream(new File(System.getProperty("user.dir") + "/n60-truststore.jks"));
            ks2.load(in2, "server".toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks2);
            in2.close();
            Enumeration<String> e2 = ks2.aliases();
            clientContext = SSLContext.getInstance("TLSv1.2");
            clientContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientContext;
    }

}
