package com.datn.be.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("cloudinary.cloud-name")
    private String cloudName;

    @Value("cloudinary.api-key")
    private String apiKey;

    @Value("cloudinary.api-secret")
    private String apiSecret;

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud-name", this.cloudName,
                "api-key", this.apiKey,
                "api-secret", this.apiSecret
        ));
    }
    
}
