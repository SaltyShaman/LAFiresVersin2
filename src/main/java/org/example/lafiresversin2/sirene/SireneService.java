package org.example.lafiresversin2.sirene;

import java.util.List;

public interface SireneService {

    SireneDTO createSirene(SireneDTO sireneDTO);
    List<SireneDTO> getAllSirens();
}
