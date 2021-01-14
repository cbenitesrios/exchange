package com.pe.bcp.exchange.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Getter
@Setter
@Builder
@Table("parameter_exchange")
public class ParameterExchange {

  @Id
  @Column("id")
  private Integer id;

  @Column("exchange_rate")
  private Double exchangeRate;

  @Column("origin_money")
  private String originMoney;

  @Column("destiny_money")
  private String destinyMoney;


}
