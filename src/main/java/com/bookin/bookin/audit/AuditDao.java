package com.bookin.bookin.audit;

import com.bookin.bookin.dao.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDao {
    @Autowired
    AuditRepository auditRepository;

    //@KafkaListener(topics="topic", groupId="myGroup")
    public void addAudit(AuditEntity audit) {
        auditRepository.save(audit);
    }
}
