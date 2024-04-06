package com.mastrminddev.springjwtsecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey;

    public JwtService() {
        this.secretKey = "aNI4wib9ZGODGvtnvq9Pxh7kbcFYJ18dhMextzC1t+c=";
    }

    public Boolean tokenValidation(String token,UserDetails userDetails){
        String username = userDetails.getUsername();
        return getUserEmail(token).equals(username) && !isTokenExpired(token);
    }

    public Boolean isTokenExpired(String token){
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public String getUserEmail(String jwt) {
        return extractClaim(jwt, Claims::getSubject);//Claims::getSubject is a reference for the getSubject method/getter in Claims class.
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey())
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims); //same as claims.getSubject(); //calling to the getSubject, a getter of claims object.
    }


    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        System.out.println("keyBytes: " + keyBytes);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
