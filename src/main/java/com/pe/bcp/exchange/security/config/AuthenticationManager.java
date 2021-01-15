package com.pe.bcp.exchange.security.config;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

  private final JWTUtils jwtUtil;

  public AuthenticationManager(JWTUtils jwtUtil) {

    this.jwtUtil = jwtUtil;

  }

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    String authToken = authentication.getCredentials().toString();


    try {
      String username = jwtUtil.getUsernameFromToken(authToken);
      if (!jwtUtil.validateToken(authToken)) {
        return Mono.empty();
      }
      Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
      List<String> rolesMap = claims.get("role", List.class);
      List<GrantedAuthority> authorities = new ArrayList<>();
      for (String roleMap : rolesMap) {
        authorities.add(new SimpleGrantedAuthority(roleMap));

      }
      return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authorities));

    } catch (Exception e) {
      log.info(e.getMessage());
      return Mono.empty();
    }
  }
}