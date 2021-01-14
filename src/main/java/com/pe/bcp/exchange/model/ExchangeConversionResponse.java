package com.pe.bcp.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExchangeConversionResponse {

  private Double originalAmount;
  private Double destinyAmount;
  private String originMoney;
  private String destinyMoney;
  private Double exchangeRate;

}
