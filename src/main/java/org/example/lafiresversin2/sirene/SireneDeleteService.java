package org.example.lafiresversin2.sirene;


import jakarta.transaction.Transactional;
import org.example.lafiresversin2.exception.SireneOperationNotAllowedException;
import org.example.lafiresversin2.fire.FireRepository;
import org.springframework.stereotype.Service;

@Service
public class SireneDeleteService {

    private final SireneRepository sireneRepository;
    private final FireRepository fireRepository;

    public SireneDeleteService(SireneRepository sireneRepository, FireRepository fireRepository) {
        this.sireneRepository = sireneRepository;
        this.fireRepository = fireRepository;
    }

    @Transactional
    public Sirene deleteSireneIfNoActiveFire(Long sireneId) {
        Sirene sirene = sireneRepository.findById(sireneId)
                .orElseThrow(() -> new IllegalArgumentException("Sirene ikke fundet med id: " + sireneId));

        boolean hasActiveFire = sirene.getFires().stream().anyMatch(fire -> !fire.isClosed());
        if (hasActiveFire) {
            throw new SireneOperationNotAllowedException("Kan ikke slette sirenen, den er tilknyttet en aktiv brand");
        }

        //Remove sirens from the fires
        sirene.getFires().forEach(fire -> fire.getSirenes().remove(sirene));
        fireRepository.saveAll(sirene.getFires());

        sireneRepository.delete(sirene);
        return sirene;
    }
}







