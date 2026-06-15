package org.natzi.maskedlady.config.filter;

public class EndPointSecurity {

    public static final String[] ALLOW_REQUEST_NO_JWT = {
            "/masked-auth/login",
            "/masked-auth/login/**",
            "/masked-auth/create-account",
            "/masked-auth/create-account/**",
            "/masked-auth/delete-user/**",
    };

    public static final String[] WITH_JWT = {
            "/masked-auth/test-hello",
    };

    public static final String[] ALLOW_SWAGGER_REQUESTS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/api_masked.yaml"
        };
}