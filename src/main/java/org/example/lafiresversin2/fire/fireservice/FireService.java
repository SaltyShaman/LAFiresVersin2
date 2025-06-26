package org.example.lafiresversin2.fire.fireservice;

import org.example.lafiresversin2.fire.fireentity.FireDTO;

import java.util.List;

public interface FireService {

    FireDTO createFire(FireDTO fireDTO);
    List<FireDTO> getAllActiveFires();
    boolean closeFire(Long fireId);
}
