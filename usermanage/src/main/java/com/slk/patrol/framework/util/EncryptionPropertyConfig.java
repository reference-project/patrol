package com.slk.patrol.framework.util;


import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

@Configuration
public class EncryptionPropertyConfig {
    @Bean(name="encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver() {
        return new EncryptionPropertyResolver();
    }
    class EncryptionPropertyResolver implements EncryptablePropertyResolver {
        @Override
        public String resolvePropertyValue(String value) {
            if(StringUtils.isBlank(value)) {
                return value;
            }
            // 值以DES@开头的均为DES加密,需要解密
            if(value.startsWith("DES@")) {
                return resolveDESValue(value.substring(4));
            }

            if(value.startsWith("AES@")) {
                encryptDESValue(value.substring(4));
            }
            // 不需要解密的值直接返回
            return value;
        }
        private void encryptDESValue(String value){
            System.out.println("---------------------------");
            System.out.println("");
            System.out.println(new SecretUtil().encrypt(value));
            System.out.println("");
            System.out.println("---------------------------");
        }
        private String resolveDESValue(String value) {
            // 自定义DES密文解密
            //DESUtil.getDecryptString(value);
            String decryptValue=null;
            try{
                decryptValue=new SecretUtil().decrypt(value);
            }catch (UnsupportedEncodingException uee){
                decryptValue=value;
            }
            return decryptValue;
        }

    }
}
