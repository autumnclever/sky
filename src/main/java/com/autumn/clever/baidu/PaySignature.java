//package com.autumn.clever.baidu;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
//
//import java.io.ByteArrayInputStream;
//import java.security.PrivateKey;
//import java.security.Signature;
//import java.security.spec.InvalidKeySpecException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import static com.autumn.clever.common.Constants.CHARSET_UTF8;
//
///**
// * @program: sky
// * @description:
// * @author: zhangqiuying
// * @create: 2023-06-16 15:07
// **/
//@Slf4j
//public class PaySignature {
//
//    /**
//     * 获取签名
//     *
//     * @param sortedParams 排序后的参数
//     * @param privateKey   私钥
//     * @return 返回签名后的字符串
//     * @throws ServiceRuntimeException
//     */
//    public static String genSignWithRsa(Map<String, String> sortedParams, String privateKey)
//            throws ServiceRuntimeException {
//        String sortedParamsContent = getSignContent(sortedParams);
//        String rsaSign = rsaSign(sortedParamsContent, privateKey, CHARSET_UTF8);
//        log.info("sign detail: sorted params=[{}]  rsa sign=[{}]", sortedParamsContent, rsaSign);
//        return rsaSign;
//    }
//
//    /**
//     * @param sortedParams 已经排序的字符串
//     * @return 返回签名后的字符串
//     */
//    public static String getSignContent(Map<String, String> sortedParams) {
//        StringBuilder content = new StringBuilder();
//        List<String> keys = new ArrayList<>(sortedParams.keySet());
//        Collections.sort(keys);
//        int index = 0;
//        for (String key : keys) {
//            String value = sortedParams.get(key);
//            content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
//            index++;
//        }
//        return content.toString();
//    }
//
//    /**
//     * sha1WithRsa 加签
//     *
//     * @param content    需要加密的字符串
//     * @param privateKey 私钥
//     * @param charset    字符编码类型  如：utf8
//     * @return
//     * @throws ServiceRuntimeException
//     */
//    private static String rsaSign(String content, String privateKey,
//                                  String charset) throws ServiceRuntimeException {
//        try {
//            PrivateKey priKey = getPrivateKeyFromPKCS8(Constants.PAY_SIGN_TYPE_RSA,
//                    new ByteArrayInputStream(privateKey.getBytes()));
//
//            Signature signature = Signature.getInstance(Constants.PAY_SIGN_ALGORITHMS);
//            signature.initSign(priKey);
//            if (StringUtils.isEmpty(charset)) {
//                signature.update(content.getBytes());
//            } else {
//                signature.update(content.getBytes(charset));
//            }
//            byte[] signed = signature.sign();
//            return new String(Base64.encodeBase64(signed));
//        } catch (InvalidKeySpecException ie) {
//            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "genSignWithRsa", "signError"),
//                    "RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥");
//        } catch (Exception e) {
//            throw new ServiceRuntimeException(new ServiceKeyword("paySignature", "genSignWithRsa", "signError"),
//                    "RSAcontent = " + content + "; charset = " + charset);
//        }
//    }
//}
