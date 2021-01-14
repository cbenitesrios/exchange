package com.pe.bcp.exchange;

import com.pe.bcp.exchange.impl.ExchangeServiceImpl;
import com.pe.bcp.exchange.model.ExchangeConversionRequest;
import com.pe.bcp.exchange.model.ExchangeConversionResponse;
import com.pe.bcp.exchange.model.ExchangeUpdateRequest;
import com.pe.bcp.exchange.model.entity.ParameterExchange;
import com.pe.bcp.exchange.repository.ParameterExchangeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ExchangeBusinessTests {


  @InjectMocks
  ExchangeServiceImpl exchangeService;

  @Mock
  ParameterExchangeRepo parameterExchangeRepo;

  ExchangeConversionRequest exchangeConversionRequest;

  ExchangeUpdateRequest exchangeUpdateRequest;

  @BeforeEach
  void setTest() {
    exchangeConversionRequest = ExchangeConversionRequest.builder()
      .amount(3.00)
      .destinyMoney("USD")
      .originMoney("PER")
      .build();

    exchangeUpdateRequest =ExchangeUpdateRequest.builder()
      .exchangeRate(3d)
      .destinyMoney("USD")
      .originMoney("PER")
      .build();
  }

  @Test
  void conversionOk() {
    Mockito.when(parameterExchangeRepo.findByOriginMoneyAndDestinyMoney("PER", "USD"))
      .thenReturn(Mono.just(ParameterExchange.builder()
        .exchangeRate(3.0)
        .destinyMoney("USD")
        .originMoney("PER")
        .build()));

    StepVerifier.create(exchangeService.getExchangeAmount(exchangeConversionRequest)).
      expectNext(
        ExchangeConversionResponse.builder()
          .destinyAmount(9d)
          .destinyMoney("USD")
          .exchangeRate(3d)
          .originalAmount(3d)
          .originMoney("PER")
          .build()
      ).verifyComplete();
  }

  @Test
  void updateOk() {
    Mockito.when(parameterExchangeRepo.findByOriginMoneyAndDestinyMoney("PER", "USD"))
      .thenReturn(Mono.just(ParameterExchange.builder()
        .exchangeRate(3.0)
        .destinyMoney("USD")
        .originMoney("PER")
        .build()));
    Mockito.when(parameterExchangeRepo.save(Mockito.any(ParameterExchange.class)))
      .thenReturn(Mono.just(
        ParameterExchange.builder()
          .id(1)
          .exchangeRate(3.0)
          .destinyMoney("USD")
          .originMoney("PER")
          .build())
      );

    StepVerifier.create(exchangeService.updateMoneyExchange(exchangeUpdateRequest)).expectNext()
      .verifyComplete();

  }


  @Test
  void conversionError() {

    Mockito.when(parameterExchangeRepo.findByOriginMoneyAndDestinyMoney(Mockito.anyString(), Mockito.anyString()))
      .thenReturn(Mono.empty());

    StepVerifier.create(exchangeService.getExchangeAmount(exchangeConversionRequest)).
      expectError().verify();

  }

  @Test
  void conversionError2() {

    Mockito.when(parameterExchangeRepo.findByOriginMoneyAndDestinyMoney("PER", "USD"))
      .thenReturn(Mono.just(ParameterExchange.builder()
        .exchangeRate(3.0)
        .destinyMoney("USD")
        .originMoney("PER")
        .build()));

    Mockito.when(parameterExchangeRepo.save(Mockito.any(ParameterExchange.class)))
      .thenReturn(Mono.empty());

    StepVerifier.create(exchangeService.updateMoneyExchange(exchangeUpdateRequest)).
      expectError().verify();

  }

}
