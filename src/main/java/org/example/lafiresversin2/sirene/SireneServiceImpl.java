package org.example.lafiresversin2.sirene;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SireneServiceImpl implements SireneService {

    private final SireneRepository sireneRepository;

    public SireneServiceImpl(SireneRepository sireneRepository) {
        this.sireneRepository = sireneRepository;
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



}
