package com.faqcodes.agilesoft.shared.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class JwtTokenProvider {

  private static final Long EXPIRATION = 300000l; // 5 minutos
  private static final String SECRET = "dSlAs6kziO4wvjf3DGRrF2rj3w32tuWbSzM6xWFS9/Y=";
  private static final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  public String createToken(String userId) {
    var now = new Date();
    var expiryDate = new Date(now.getTime() + EXPIRATION);

    return Jwts.builder()
        // .setClaims(claims)
        .setSubject(userId)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SIGNING_KEY)
        .compact();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token);

    return true;
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();

    String username = claims.getSubject();

    return new UsernamePasswordAuthenticationToken(username, null, null);
  }

}
