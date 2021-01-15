package com.pe.bcp.exchange.web;

import com.pe.bcp.exchange.business.ExchangeService;
import com.pe.bcp.exchange.model.ExchangeConversionRequest;
import com.pe.bcp.exchange.model.ExchangeConversionResponse;
import com.pe.bcp.exchange.model.ExchangeUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("exchange")
@AllArgsConstructor
@Validated
@Slf4j
public class ExchangeController {

  ExchangeService exchangeService;

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public Mono<ExchangeConversionResponse> getExchange(@Validated @RequestBody ExchangeConversionRequest exchangeConversionRequest) {
    return exchangeService.getExchangeAmount(exchangeConversionRequest);
  }

  @PutMapping
  @PreAuthorize("hasRole('USER')")
  public Mono<Void> updateMoneyExchange(@Validated @RequestBody ExchangeUpdateRequest exchageUpdateDto) {
    return exchangeService.updateMoneyExchange(exchageUpdateDto);
  }

}
