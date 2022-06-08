package com.posco.insta.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private final String SECRET_KEY = "SECRET_KEYSECRET_KEYSECRET_KEYSECRET_KEYSECRET_KEY";
    public String createToken(String subject, long expTime){
        if(expTime<=0){
            throw new RuntimeException();

        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signatureKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis()+expTime)).compact();
    }
    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return  claims.getSubject();
    }
}
