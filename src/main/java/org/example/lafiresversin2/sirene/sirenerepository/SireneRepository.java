package org.example.lafiresversin2.sirene.sirenerepository;

import org.example.lafiresversin2.sirene.sireneentity.Sirene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SireneRepository extends JpaRepository<Sirene, Long> {

}
