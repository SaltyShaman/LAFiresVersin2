package org.example.lafiresversin2.fire;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireRepository extends JpaRepository<Fire, Long> {
    List<Fire> findByClosedFalse(); //custom method due to boolean

}
