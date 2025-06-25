package org.example.lafiresversin2.sirene;


import org.springframework.stereotype.Service;

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


}
