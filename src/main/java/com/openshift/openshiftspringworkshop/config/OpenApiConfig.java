package com.openshift.openshiftspringworkshop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
public class OpenApiConfig {

    public static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    //IF name is changed here, make sure to change it also in the console_log_pattern in
    // application_dev or prod.yml to be able to log the id's
    public static final String CORRELATION_ID_LOG_VAR_NAME = "CID";

    @Value("${application.version:unknown}")
    private String applicationVersion;

    @Autowired
    private com.openshift.openshiftspringworkshop.config.SwaggerProperties swaggerProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact().name(swaggerProperties.getContactname()))
                .license(new License().name(swaggerProperties.getLicense()).url(""))
                .version(applicationVersion);

        Components components = new Components();
        components.setParameters(globalParametersForComponentsToReuse());

        return new OpenAPI()
                .components(components)
                .info(info);
    }

    public Map<String, Parameter> globalParametersForComponentsToReuse() {
        Map<String, Parameter> globalParametersToReuse = new HashMap<>();

        Parameter parameterCID = new HeaderParameter();
        parameterCID.name(CORRELATION_ID_HEADER_NAME);
        parameterCID.description("To follow the flow in the logfiles");
        parameterCID.schema(new StringSchema());
        parameterCID.required(false);
        globalParametersToReuse.put("requestHeaderCID", parameterCID);

        return globalParametersToReuse;
    }

    public Map<String, ApiResponse> globalResponsesForComponentsToReuse() {
        return new HashMap<>();
    }

}
