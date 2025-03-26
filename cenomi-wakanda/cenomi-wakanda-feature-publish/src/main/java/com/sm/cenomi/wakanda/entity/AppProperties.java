package com.sm.cenomi.wakanda.entity;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.*;

@Table(value = "app_properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AppProperties {

  @Column(value = "application")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String application;

  @Column(value = "profile")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String profile;

  @Column(value = "label")
  @PrimaryKeyColumn(type = PARTITIONED)
  private String label;

  @PrimaryKeyColumn(name = "config_key")
  private String configKey;

  @PrimaryKeyColumn(name = "config_value")
  private String configValue;

  @Column(value = "description")
  @PrimaryKeyColumn
  private String description;
}
