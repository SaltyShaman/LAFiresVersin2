package org.example.lafiresversin2.sirene.sirenerepository;

import jakarta.transaction.Transactional;
import org.example.lafiresversin2.sirene.sireneentity.Sirene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SireneRepository extends JpaRepository<Sirene, Long> {

    @Modifying
    @Transactional
    @Query("""
    UPDATE Sirene s SET s.status = 'IDLE'
    WHERE s.status <> 'IDLE' AND NOT EXISTS (
        SELECT f FROM s.fires f WHERE f.closed = false
    )
""")
    int bulkDeactivateSirensWithoutOpenFires();


}
