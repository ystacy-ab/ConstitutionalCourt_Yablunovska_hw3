package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@Table("Ruling")
public class Ruling implements Persistable<Integer> {

    @Id
    @Column("RulingID")
    private Integer rulingId;

    @Column("VerdictDate")
    private LocalDate verdictDate;

    @Column("VerdictText")
    private String verdictText;

    @Column("CaseNumber")
    private Integer caseNumber;

    @Transient
    private boolean isNewRecord = true;

    @Override
    public Integer getId() { return rulingId; }

    @Override
    public boolean isNew() { return isNewRecord; }

    public void markAsExisting() { this.isNewRecord = false; }
}