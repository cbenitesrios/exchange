package com.pe.bcp.exchange.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeUpdateRequest {

  @NotNull(message = "First name cannot be missing or empty")
  @Size(min = 3, max = 3, message = "La moneda de origen debe ser de 3 letras.")
  @Pattern(regexp = "[A-Z]+", message = "El campo debe tener letras en mayúscula")
  private String originMoney;

  @NotNull(message = "La moneda de destino no puede ser estar vacía")
  @Size(min = 3, max = 3, message = "La moneda de destino debe ser de 3 letras.")
  @Pattern(regexp = "[A-Z]+", message = "El campo debe tener letras en mayúscula")
  private String destinyMoney;

  @NotNull(message = "El monto debe de ser mayor a 0")
  @Positive(message = "El monto debe de ser mayor a 0")
  private Double exchangeRate;

}
