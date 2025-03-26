package com.sm.cenomi.wakanda.repository;

import com.sm.cenomi.wakanda.entity.AuditLog;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface AuditLogRepository extends ReactiveCassandraRepository<AuditLog, String> {}
