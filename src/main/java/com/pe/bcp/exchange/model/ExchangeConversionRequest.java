package com.pe.bcp.exchange.model;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class ExchangeConversionRequest {

  @NotNull(message = "El monto debe de ser mayor a 0")
  @Positive(message = "El monto debe de ser mayor a 0")
  private Double amount;

  @NotNull(message = "La moneda de origen no debe estar vacía.")
  @Size(min = 3, max = 3, message = "La moneda de origen debe ser de 3 letras.")
  @Pattern(regexp = "[A-Z]+", message = "El campo debe tener letras en mayúscula")
  private String originMoney;

  @NotNull(message = "La moneda de destino no puede ser estar vacía")
  @Size(min = 3, max = 3, message = "La moneda de destino debe ser de 3 letras.")
  @Pattern(regexp = "[A-Z]+", message = "El campo debe tener letras en mayúscula")
  private String destinyMoney;

}
