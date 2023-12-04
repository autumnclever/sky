package com.autumn.clever.baidu.cashier;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-10 22:42
 **/
public class PaySignature {

    private static Logger log = LoggerFactory.getLogger(PaySignature.class);

    /**
     * 获取签名
     *
     * @param sortedParams 排序后的参数
     * @param privateKey   私钥
     * @return 返回签名后的字符串
     * @throws ServiceRuntimeException
     */
    public static String genSignWithRsa(Map<String, String> sortedParams, String privateKey) throws
            ServiceRuntimeException {
        String sortedParamsContent = getSignContent(sortedParams);
        String rsaSign = rsaSign(sortedParamsContent, privateKey, Constants.CHARSET_UTF8);
        log.info("sign detail: sorted params=[{}]  rsa sign=[{}]", sortedParamsContent, rsaSign);
        return rsaSign;
    }


    /**
     * 获取签名，参数值为object类型
     *
     * @param reqParams
     * @param privateKey
     * @return
     */
    public static Object genSignWithRsaObj(Map<String, Object> reqParams, String privateKey) {
        String sortedParamsContent = getSignContentObj(reqParams);
        String rsaSign = rsaSign(sortedParamsContent, privateKey, Constants.CHARSET_UTF8);
        log.info("sign detail: sorted params=[{}]  rsa sign=[{}]", sortedParamsContent, rsaSign);
        return rsaSign;
    }

    /**
     * 私钥加密
     *
     * @param data          加密前的字符串
     * @param privateKeyStr 私钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptByPriKey(String data, String privateKeyStr) {
        try {
            byte[] priKey = Base64.decodeBase64(privateKeyStr);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
            KeyFactory keyFactory = KeyFactory.getInstance(Constants.PAY_SIGN_TYPE_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encryptBytes = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(encryptBytes);
        } catch (InvalidKeySpecException ie) {
            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "encryptByPriKey", "signError"),
                    "RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥");
        } catch (Exception e) {
            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "encryptByPriKey", "signError"),
                    "RSAcontent = " + data);
        }
    }

//    public static void main(String[] args) {
//        Map<String, String> sortedParams = new HashMap<>();
//        sortedParams.put("unitPrice","10000");
//        sortedParams.put("orderId","103391692583399");
//        sortedParams.put("payTime","103391692583399");
//        checkSignWithRsa();
//    }

    /**
     * 签名验证
     *
     * @param sortedParams 参数
     * @param pubKey       对应于商家的服务公钥
     * @param sign         签名
     * @return 签名结果
     * @throws ServiceRuntimeException 验证签名异常
     */
    public static boolean checkSignWithRsa(Map<String, String> sortedParams, String pubKey, String sign)
            throws ServiceRuntimeException {
        String sortedParamsContent = getSignContent(sortedParams);
        return doCheck(sortedParamsContent, sign, pubKey, Constants.CHARSET_UTF8);
    }

    /**
     * @param sortedParams 已经排序的字符串
     * @return 返回签名后的字符串
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            String value = sortedParams.get(key);
            content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
            index++;
        }
        return content.toString();
    }

    /**
     * sha1WithRsa 加签
     *
     * @param content    需要加密的字符串
     * @param privateKey 私钥
     * @param charset    字符编码类型  如：utf8
     * @return
     * @throws ServiceRuntimeException
     */
    public static String rsaSign(String content, String privateKey,
                                  String charset) throws ServiceRuntimeException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(Constants.PAY_SIGN_TYPE_RSA,
                    new ByteArrayInputStream(privateKey.getBytes()));

            Signature signature = Signature.getInstance(Constants.PAY_SIGN_ALGORITHMS);
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (InvalidKeySpecException ie) {
            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "genSignWithRsa", "signError"),
                    "RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥");
        } catch (Exception e) {
            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "genSignWithRsa", "signError"),
                    "RSAcontent = " + content + "; charset = " + charset);
        }
    }

    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] encodedKey = IOUtils.toByteArray(ins);
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     * @throws ServiceRuntimeException
     */
    private static boolean doCheck(String content, String sign, String publicKey, String encode)
            throws ServiceRuntimeException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(Constants.PAY_SIGN_TYPE_RSA);
            byte[] bytes = publicKey.getBytes();
            byte[] encodedKey = Base64.decodeBase64(bytes);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(Constants.PAY_SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "checkSignWithRsa", "checkRsaSign"),
                    "RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥");
        }
    }

    /**
     * 获取拼接字符串
     *
     * @param reqParams
     * @return
     */
    private static String getSignContentObj(Map<String, Object> reqParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(reqParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (String key : keys) {
            String value = reqParams.get(key).toString();
            content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
            index++;
        }
        return content.toString();
    }
}
