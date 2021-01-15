package com.pe.bcp.exchange.security.service;

import com.pe.bcp.exchange.security.model.AccessResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

  Mono<AccessResponse> getUser(String user, String password);
}
