package com.pe.bcp.exchange.security.web;

import com.pe.bcp.exchange.security.model.AccessRequest;
import com.pe.bcp.exchange.security.model.AccessResponse;
import com.pe.bcp.exchange.security.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {

    this.authService = authService;
  }


  @PostMapping("/login")
  public Mono<AccessResponse> login(@RequestBody AccessRequest access) {
    return authService.getUser(access.getUsername(), access.getPassword());
  }
}
