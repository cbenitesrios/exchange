package com.pe.bcp.exchange.security.service.impl;

import com.pe.bcp.exchange.security.config.JWTUtils;
import com.pe.bcp.exchange.security.model.AccessResponse;
import com.pe.bcp.exchange.security.model.Role;
import com.pe.bcp.exchange.security.model.User;
import com.pe.bcp.exchange.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

  @Value("${user}")
  private String username;

  @Value("${pass}")
  private String password;


  private final JWTUtils jwtUtils;

  public AuthServiceImpl(JWTUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Mono<AccessResponse> getUser(String user, String password) {
    if (this.username.equalsIgnoreCase(user) && this.password.equalsIgnoreCase(password)) {
      return Mono.just(new AccessResponse(jwtUtils.generateToken(new User(username, Arrays.asList(Role.ROLE_USER)))));
    } else {
      return Mono.empty();
    }
  }
}
