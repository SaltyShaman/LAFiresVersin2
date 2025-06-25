package org.example.lafiresversin2.sirene;


import org.example.lafiresversin2.fire.Fire;
import org.example.lafiresversin2.fire.FireRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SireneServiceImpl implements SireneService {

    private final SireneRepository sireneRepository;
    private final FireRepository fireRepository;

    public SireneServiceImpl(SireneRepository sireneRepository, FireRepository fireRepository) {
        this.sireneRepository = sireneRepository;
        this.fireRepository = fireRepository;
    }

    @Override
    public SireneDTO createSirene(SireneDTO sireneDTO) {
        Sirene sirene = SireneMapper.toEntity(sireneDTO);
        sirene = sireneRepository.save(sirene);
        return SireneMapper.toDTO(sirene);
    }

    @Override
    public List<SireneDTO> getAllSirens(){
        List<Sirene> sirenes = sireneRepository.findAll();
        return sirenes.stream().
                map(SireneMapper::toDTO).
                collect(Collectors.toList());
    }

    @Override
    public void deleteSiren(Long sireneId) {
        Sirene sirene = sireneRepository.findById(sireneId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sirene not found with id " + sireneId));

        //Remove fires from sirene on an individual level
        for (Fire fire : sirene.getFires()) {
            fire.getSirenes().remove(sirene);
        }

        sirene.getFires().clear();

        // Optional but recommended: save the affected fires to persist the update
        fireRepository.saveAll(sirene.getFires());

        sireneRepository.delete(sirene);
    }


}
