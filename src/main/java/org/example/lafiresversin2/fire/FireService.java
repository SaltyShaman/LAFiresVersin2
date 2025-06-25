package org.example.lafiresversin2.fire;

import java.util.List;

public interface FireService {

    FireDTO createFire(FireDTO fireDTO);
    List<FireDTO> getAllActiveFires();
    boolean closeFire(Long fireId);
}
