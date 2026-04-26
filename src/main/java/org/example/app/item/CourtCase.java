package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDate;

@Data
@Table("CourtCase")
public class CourtCase implements Persistable<Integer> {

    @Id
    @Column("CaseNumber")
    private Integer caseNumber;

    @Column("CaseTitle")
    private String caseTitle;

    @Column("FilingDate")
    private LocalDate filingDate;

    @Column("Status")
    private String status;

    @Column("PetitionerID")
    private Integer petitionerId;

    @Transient
    private boolean isNewRecord = true;

    @Override
    public Integer getId() {
        return caseNumber;
    }

    @Override
    public boolean isNew() {
        return isNewRecord;
    }

    public void markAsExisting() {
        this.isNewRecord = false;
    }
}