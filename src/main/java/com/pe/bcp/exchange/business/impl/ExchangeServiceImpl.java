package com.pe.bcp.exchange.business.impl;

import com.pe.bcp.exchange.business.ExchangeService;
import com.pe.bcp.exchange.model.ExchangeConversionRequest;
import com.pe.bcp.exchange.model.ExchangeConversionResponse;
import com.pe.bcp.exchange.model.ExchangeUpdateRequest;
import com.pe.bcp.exchange.model.entity.ParameterExchange;
import com.pe.bcp.exchange.repository.ParameterExchangeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.pe.bcp.exchange.util.Constants.NOT_FOUND_EXCHANGE;
import static com.pe.bcp.exchange.util.Constants.UPDATE_EXCHANGE_ERROR;
import static com.pe.bcp.exchange.util.Utils.exchangeConversion;

@Service
@AllArgsConstructor
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

  private final ParameterExchangeRepo parameterExchangeRepo;



  /*
  *   Busca en la tabla el tipo de cambio basandose en la moneda de origen y de destino.
  *   Si no la encuentra emite un error.
  *   Luego calcula el monto con el tipo de cambio en base a monto de origen y el tipo de cambio.
  */
  public Mono<ExchangeConversionResponse> getExchangeAmount(ExchangeConversionRequest exchangeRequestDto) {

    log.info("Request -> {} ", exchangeRequestDto.toString());

    return parameterExchangeRepo.findByOriginMoneyAndDestinyMoney(exchangeRequestDto.getOriginMoney(),
      exchangeRequestDto.getDestinyMoney())
      .switchIfEmpty(emptyResponseError())
      .map(parameterExchange -> exchangeConversion(parameterExchange, exchangeRequestDto.getAmount()));
  }

  /*
   *   Busca en la tabla el tipo de cambio basandose en la moneda de origen y de destino.
   *   Si no la encuentra emite un error.
   *   Luego actualiza el tipo de cambio y lo guarda en la base de datos.
   */
  public Mono<Void> updateMoneyExchange(ExchangeUpdateRequest exchangeUpdateRequest) {

    log.info("Request -> {} ", exchangeUpdateRequest.toString());

    return parameterExchangeRepo.findByOriginMoneyAndDestinyMoney(exchangeUpdateRequest.getOriginMoney(),
      exchangeUpdateRequest.getDestinyMoney())
      .switchIfEmpty(emptyResponseError())
      .flatMap(exchangeResult -> {
        exchangeResult.setExchangeRate(exchangeUpdateRequest.getExchangeRate());
        return parameterExchangeRepo.save(exchangeResult);
      })
      .switchIfEmpty(updateResponseError())
      .then();

  }


  private Mono<ParameterExchange> emptyResponseError() {
    return Mono.error(new Exception(NOT_FOUND_EXCHANGE));
  }

  private Mono<ParameterExchange> updateResponseError() {
    return Mono.error(new Exception(UPDATE_EXCHANGE_ERROR));
  }

}
