package org.example.lafiresversin2.sirene.sireneservice;

import jakarta.transaction.Transactional;
import org.example.lafiresversin2.exception.SireneOperationNotAllowedException;
import org.example.lafiresversin2.fire.FireRepository;
import org.example.lafiresversin2.sirene.sireneentity.Sirene;
import org.example.lafiresversin2.sirene.sireneentity.SireneDTO;
import org.example.lafiresversin2.sirene.sirenerepository.SireneRepository;
import org.springframework.stereotype.Service;

@Service
public class SireneUpdateService {

    private final SireneRepository sireneRepository;
    private final FireRepository fireRepository;

    public SireneUpdateService(SireneRepository sireneRepository, FireRepository fireRepository) {
        this.sireneRepository = sireneRepository;
        this.fireRepository = fireRepository;
    }


    @Transactional
    public Sirene updateSireneIfNoActiveFire(Long sireneId, SireneDTO sireneDTO) {
        Sirene existingSirene = sireneRepository.findById(sireneId)
                .orElseThrow(() -> new IllegalArgumentException("Sirene ikke fundet med id: " + sireneId));

        boolean hasActiveFire = existingSirene.getFires().stream().anyMatch(fire -> !fire.isClosed());
        if (hasActiveFire) {
            throw new SireneOperationNotAllowedException("Kan ikke opdatere sirenen, den er tilknyttet en aktiv brand");
        }

        existingSirene.setLatitude(sireneDTO.getLatitude());
        existingSirene.setLongitude(sireneDTO.getLongitude());
        existingSirene.setStatus(sireneDTO.getStatus());

        return sireneRepository.save(existingSirene);
    }

}
