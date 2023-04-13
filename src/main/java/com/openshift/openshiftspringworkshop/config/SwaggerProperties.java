package com.openshift.openshiftspringworkshop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String title;

    private String description;

    private String license;

    private String contactname;

}
