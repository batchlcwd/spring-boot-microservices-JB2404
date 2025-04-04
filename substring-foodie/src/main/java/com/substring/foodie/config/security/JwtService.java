package com.substring.foodie.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//perform operations with jwt
// create--
// token se jwt
// validate

@Service
public class JwtService {


    //mills
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;//for access token --> 15 minutes
    private static final long EXPIRATION_REFRESH_TIME = 24 * 60 * 60 * 1000; // for refresh token --> 24 hours
    private static final String SECRET = "sgvasfwtacwetvcsgsghscsvtswtvwtvesgxwctvecxwtgvehcvyescevyusecevryeye";

    private static final String REFRESH_TOKEN_TYPE = "refresh_token";
    private static final String ACCESS_TOKEN_TYPE = "access_token";

    //generate token
    public String generateToken(String username, boolean isAccessToken) {

        long expTime = isAccessToken ? EXPIRATION_TIME : EXPIRATION_REFRESH_TIME;
        String tokenType = isAccessToken ? ACCESS_TOKEN_TYPE : REFRESH_TOKEN_TYPE;
        Map<String, Object> claims = new HashMap<>();
        claims.put("typ", tokenType);


        String token =
                Jwts.builder()
                        .setClaims(claims)
                        .setSubject(username)
                        .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expTime)).signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256).compact();
        return token;

    }

    // get username from token
    public String getUsername(String token) {
        String username = Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token)
                .getBody()
                .getSubject();
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
        Date expiration = Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    public boolean isRefreshToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody();
        String tokenType = (String) claims.get("typ");
        if (tokenType == null) return false;
        return tokenType.equals(REFRESH_TOKEN_TYPE);


    }

    public boolean isAccessToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody();
        String tokenType = (String) claims.get("typ");
        if (tokenType == null) return false;
        return tokenType.equals(ACCESS_TOKEN_TYPE);
    }


}
