package org.natzi.maskedlady.config.filter;

public class EndPointSecurity {

    public static final String[] PERMIT_SN_JWT = {
            "/masked-auth/login",
            "/masked-auth/login/**",
            "/masked-auth/create-account",
            "/masked-auth/create-account/**"
    };

    public static final String[] WITH_JWT = {
            "/masked-auth/test-hello",
    };
}
