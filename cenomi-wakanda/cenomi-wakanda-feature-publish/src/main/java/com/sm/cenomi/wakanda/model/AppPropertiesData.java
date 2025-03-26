package com.sm.cenomi.wakanda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppPropertiesData {

  @JsonProperty("application")
  private String application;

  @JsonProperty("config_key")
  private String configKey;

  @JsonProperty("config_value")
  private String configValue;

  @JsonProperty("description")
  private String description = application + configKey + "_desc";

  @JsonProperty("profile")
  private String profile;

  @JsonProperty("label")
  private String label;
}
