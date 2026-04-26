package org.example.app.item;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Judge")
public class Judge {

    @Id
    @Column("JudgeID")
    private Integer judgeId;

    @Column("FirstName")
    private String firstName;

    @Column("LastName")
    private String lastName;
}