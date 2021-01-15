package com.pe.bcp.exchange.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessRequest {

  @NotNull(message = "El usuario no debe ser vacío.")
  @Size(min = 1, message = "El usuario no debe ser vacío.")
  private String username;

  @NotNull(message = "La constraseña no debe ser vacío.")
  @Size(min = 1, message = "La constraseña no debe ser vacío.")
  private String password;
}
