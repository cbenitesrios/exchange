package com.pe.bcp.exchange.repository;

import com.pe.bcp.exchange.model.entity.ParameterExchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ParameterExchangeRepo extends ReactiveCrudRepository<ParameterExchange, Integer> {


  Mono<ParameterExchange> findByOriginMoneyAndDestinyMoney(String originMoney, String destinyMoney);

}
