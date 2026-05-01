package org.example.app.item;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PetitionerRepository extends CrudRepository<Petitioner, Integer> {

    @Query("""
        SELECT p.PetitionerID        AS petitioner_id,
               p.PetitionerName      AS petitioner_name,
               p.PetitionerType      AS petitioner_type,
               ip.PassportID         AS passport_id,
               op.RegistrationNumber AS registration_number
        FROM Petitioner p
        LEFT JOIN IndividualPetitioner ip ON p.PetitionerID = ip.PetitionerID
        LEFT JOIN OrganizationPetitioner op ON p.PetitionerID = op.PetitionerID
        """)
    List<PetitionerView> findAllWithDetails();
}