package com.pe.bcp.exchange.util;

import com.pe.bcp.exchange.model.ExchangeConversionResponse;
import com.pe.bcp.exchange.model.entity.ParameterExchange;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

  public static ExchangeConversionResponse exchangeConversion(ParameterExchange parameterExchange, Double originalAmount) {

    return ExchangeConversionResponse.builder()
      .exchangeRate(parameterExchange.getExchangeRate())
      .destinyMoney(parameterExchange.getDestinyMoney())
      .originMoney(parameterExchange.getOriginMoney())
      .originalAmount(originalAmount)
      .destinyAmount(exchangeConversion(originalAmount, parameterExchange.getExchangeRate()))
      .build();

  }


  private static Double exchangeConversion(Double originAmount, Double exchangeRate) {
    return originAmount * exchangeRate;
  }


}
