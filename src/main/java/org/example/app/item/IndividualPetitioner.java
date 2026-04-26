package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("IndividualPetitioner")
public class IndividualPetitioner implements Persistable<Integer> {

    @Id
    @Column("PetitionerID")
    private Integer petitionerId;

    @Column("PassportID")
    private String passportId;

    @Transient
    private boolean isNewRecord = true;

    @Override
    public Integer getId() { return petitionerId; }

    @Override
    public boolean isNew() { return isNewRecord; }
}