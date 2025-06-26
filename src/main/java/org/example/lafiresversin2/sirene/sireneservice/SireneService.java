package org.example.lafiresversin2.sirene.sireneservice;

import org.example.lafiresversin2.sirene.sireneentity.SireneDTO;

import java.util.List;

public interface SireneService {

    SireneDTO createSirene(SireneDTO sireneDTO);
    List<SireneDTO> getAllSirens();
    void deleteSiren(Long sireneId);
    void updateSiren(Long sireneId, SireneDTO sireneDTO);
}
