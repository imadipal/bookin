package com.bookin.bookin.service;

import com.bookin.bookin.audit.AuditDao;
import com.bookin.bookin.audit.AuditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuditServiceImplTest {
    @InjectMocks
    private AuditServiceImpl auditServiceImpl;

    @Mock
    AuditDao AuditDao;

    @Mock
    ObjectMapper mapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsume() throws JsonProcessingException {
        String message="{\"bookId\":\"123\",\"updatedOn\":\"2012-10-21T00:00:00+05:30\",\"action\":0}";
        doNothing().when(AuditDao).addAudit(any());
        when(mapper.readValue(message,AuditEntity.class)).thenReturn(new AuditEntity());

        auditServiceImpl.consume(message);

        verify(AuditDao).addAudit(any());

    }
}

