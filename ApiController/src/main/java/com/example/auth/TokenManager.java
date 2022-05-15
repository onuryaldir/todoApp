package com.example.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {

    private static final int validity = 30 * 60 * 1000;


    private static String secret="alperonurbiteslegends2022birazdahauzattamamozurdilerim";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("www.haydikodlayalim.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean tokenValidate(String token) {
        if (getUsernameToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public String getUsernameToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }

}
