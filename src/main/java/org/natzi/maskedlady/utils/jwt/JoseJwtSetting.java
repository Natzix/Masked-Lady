package org.natzi.maskedlady.utils.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.natzi.maskedlady.entity.OneTimeTokenLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JoseJwtSetting {

    @Value("${jwt.secret.key}")
    public byte[] secretKey;

    public String buildJWT(OneTimeTokenLogin ott) throws JOSEException {
        JWSSigner signer = new MACSigner(secretKey);

        JWSObject  jwsObject = new JWSObject(
                new JWSHeader(JWSAlgorithm.HS256),
                new Payload(loadClaim(ott).toJSONObject())
        );

        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    public JWTClaimsSet loadClaim(OneTimeTokenLogin ott) {
        Date expire = Date.from(LocalDateTime.now()
                .plusMinutes(15)
                .atZone(ZoneId.systemDefault())
                .toInstant()
        );

        return new JWTClaimsSet.Builder()
                .subject(ott.getUsername())
                .issuer("masked-lady") // ayuda a identificar la entidad que creo el jwt
                .issueTime(new Date())
                .expirationTime(expire)
                .build();
    }
}
