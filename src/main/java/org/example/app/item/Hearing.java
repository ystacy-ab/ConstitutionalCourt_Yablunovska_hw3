package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Data
@Table("Hearing")
public class Hearing implements Persistable<Integer> {

    @Id
    @Column("HearingID")
    private Integer hearingId;

    @Column("HearingDate")
    private LocalDate hearingDate;

    @Column("Location")
    private String location;

    @Column("CaseNumber")
    private Integer caseNumber;

    @Transient
    private boolean isNewRecord = true;

    @Override
    public Integer getId() { return hearingId; }

    @Override
    public boolean isNew() { return isNewRecord; }

    public void markAsExisting() { this.isNewRecord = false; }
}