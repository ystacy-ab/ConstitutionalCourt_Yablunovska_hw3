package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Petitioner")
public class Petitioner implements Persistable<Integer> {

    @Id
    @Column("PetitionerID")
    private Integer petitionerId;

    @Column("PetitionerName")
    private String petitionerName;

    @Column("PetitionerType")
    private String petitionerType;

    @Transient
    private boolean isNewRecord = true;

    @Override
    public Integer getId() { return petitionerId; }

    @Override
    public boolean isNew() { return isNewRecord; }

    public void markAsExisting() { this.isNewRecord = false; }
}