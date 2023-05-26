package com.bookin.bookin.audit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import javax.persistence.*;
import java.time.Instant;
@Entity
@Getter
@Setter
@Table(name="auditRecord")
@NoArgsConstructor
public class AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long auditId;
    @Column(name="bookId")
    Long bookId;

    @Column(name="timestamp")
    Timestamp updatedOn=Timestamp.from(Instant.now());;

    @Column(name="action")
    String action;

    public AuditEntity(Long bookid, String action) {
        this.bookId = bookid;
        this.action = action;
    }


}