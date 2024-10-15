package com.jh.www.imgmanage.config.egovconfig;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class Encryption {
    private String algorithm = "SHA-256";
    public void setAlgorithm(String algorithm) {
        this.algorithm =algorithm;

    }
    public String getAlgorithm() {
        return this.algorithm;
    }


    public String encryptPassword(String plainPassword) {
        ConfigurablePasswordEncryptor encoder = new ConfigurablePasswordEncryptor();

        encoder.setAlgorithm(this.algorithm);
        encoder.setPlainDigest(true);
        return encoder.encryptPassword(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        ConfigurablePasswordEncryptor encoder = new ConfigurablePasswordEncryptor();
        encoder.setAlgorithm(this.algorithm);
        encoder.setPlainDigest(true);

        return encoder.checkPassword(plainPassword, encryptedPassword);

    }
}
