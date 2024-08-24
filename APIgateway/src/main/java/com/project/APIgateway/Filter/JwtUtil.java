package com.project.APIgateway.Filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUtil {

//    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public void validateToken(final String token) {
        try {
//            logger.info("Validating token: {}", token);
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
//            logger.info("Token validation successful");
        } catch (SignatureException e) {
//            logger.error("Invalid JWT signature");
            throw new RuntimeException("Invalid JWT signature");
        } catch (Exception e) {
//            logger.error("Invalid JWT token");
            throw new RuntimeException("Invalid JWT token");
        }
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
