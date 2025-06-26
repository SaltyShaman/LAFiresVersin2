package org.example.lafiresversin2.sirene.sireneservice;


import org.example.lafiresversin2.fire.FireRepository;
import org.example.lafiresversin2.sirene.sireneentity.Sirene;
import org.example.lafiresversin2.sirene.sireneentity.SireneDTO;
import org.example.lafiresversin2.sirene.sireneentity.SireneMapper;
import org.example.lafiresversin2.sirene.sirenerepository.SireneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SireneServiceImpl implements SireneService {

    private final SireneRepository sireneRepository;
    private final FireRepository fireRepository;
    private final SireneDeleteService sireneDeleteService;
    private final SireneUpdateService sireneUpdateService;

    public SireneServiceImpl(SireneRepository sireneRepository, FireRepository fireRepository,
                             SireneDeleteService sireneDeleteService, SireneUpdateService sireneUpdateService ) {
        this.sireneRepository = sireneRepository;
        this.fireRepository = fireRepository;
        this.sireneDeleteService = sireneDeleteService;
        this.sireneUpdateService = sireneUpdateService;
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
        return sirenes.stream()
                        .map(SireneMapper::toDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public void deleteSiren(Long sireneId) {
        try {
            sireneDeleteService.deleteSireneIfNoActiveFire(sireneId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @Override
    public void updateSiren(Long sireneId, SireneDTO sireneDTO) {
        try {
            sireneUpdateService.updateSireneIfNoActiveFire(sireneId, sireneDTO);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



}
