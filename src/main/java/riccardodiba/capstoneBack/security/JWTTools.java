package riccardodiba.capstoneBack.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.UnauthorizedException;

import java.util.Date;
import java.util.Scanner;

@Component
public class JWTTools {
    @Value("${JWT_SECRET}")
    private String secret;


    public String createToken(User user) {
        return Jwts.builder().subject(String.valueOf(user.getId())) // Subject <-- A chi appartiene il token (id dell'utente)
                .issuedAt(new Date(System.currentTimeMillis())) // Data di emissione (IAT - Issued At)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Data di scadenza (Expiration Date)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firmo il token
                .compact();
    }


    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
        }
    }

    public String extractIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
