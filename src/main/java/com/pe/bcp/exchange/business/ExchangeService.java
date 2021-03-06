package com.pe.bcp.exchange.business;

import com.pe.bcp.exchange.model.ExchangeConversionRequest;
import com.pe.bcp.exchange.model.ExchangeConversionResponse;
import com.pe.bcp.exchange.model.ExchangeUpdateRequest;
import reactor.core.publisher.Mono;

public interface ExchangeService {

  Mono<ExchangeConversionResponse> getExchangeAmount(ExchangeConversionRequest exchangeConversionRequest);

  Mono<Void> updateMoneyExchange(ExchangeUpdateRequest exchangeUpdateRequest);
}
