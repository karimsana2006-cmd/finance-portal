package com.financeportal.finance_portal;
import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

    private String secretKey= "meowsecretkeyteherawrrrrrrrrrrwoffowfoofofwofwofowfo";

    public String generateToken(String email){
        return Jwts.builder()        // "start building a token"

                .subject(email)    // "this token belongs to this email"
                .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 24))  // "this token expires at this time"
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))      // "sign it with this secret key"
                .compact();


    }

    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))

                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();



    }


        public boolean isTokenValid(String token) {
            try {
                extractEmail(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }


    }


