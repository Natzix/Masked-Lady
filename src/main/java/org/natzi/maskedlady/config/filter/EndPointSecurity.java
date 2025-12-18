package org.natzi.maskedlady.config.filter;

public class EndPointSecurity {

    public static final String[] PERMIT_SN_JWT = {
        "/masked-auth/**"
    };

    public static final String[] ENDPOINT = {
        "/hello"
    };
}
