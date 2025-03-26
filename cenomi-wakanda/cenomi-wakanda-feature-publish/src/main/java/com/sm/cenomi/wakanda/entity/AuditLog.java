package com.sm.cenomi.wakanda.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("audit_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuditLog {

  @Id private String logId;
  private String createdBy = "SYSTEM";
  private String fileName;
  private LocalDateTime dateExecuted = LocalDateTime.now();
  private String checksum;

}