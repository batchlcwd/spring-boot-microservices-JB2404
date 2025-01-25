package com.substring.foodie.security;

import com.substring.foodie.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

//perform operations with jwt
// create--
// token se jwt
// validate

@Service
public class JwtService {


    //mills
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;//minutes
    private static final String SECRET = "sgvasfwtacwetvcsgsghscsvtswtvwtvesgxwctvecxwtgvehcvyescevyusecevryeye";


    //generate token
    public String generateToken(String username) {

        String token = Jwts.builder()
                .setSubject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return token;

    }

    // get username from token
    public String getUsername(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
        return username;
    }


    //validate token

    public boolean validateToken(String token) {
        if (this.isTokenExpired(token)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }


}
