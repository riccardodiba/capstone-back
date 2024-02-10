package riccardodiba.capstoneBack.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardodiba.capstoneBack.entities.Utente;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String secret;


    public String createToken(Utente utente) {
        String ruoli = String.valueOf(utente.getRuoli());
        return Jwts.builder().subject(String.valueOf(utente.getId()))
                .claim("ruoli", ruoli)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}