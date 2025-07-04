package org.example.lafiresversin2.sirene.sireneservice;


import jakarta.transaction.Transactional;
import org.example.lafiresversin2.fire.firerepository.FireRepository;
import org.example.lafiresversin2.sirene.sireneentity.SirenStatus;
import org.example.lafiresversin2.sirene.sireneentity.Sirene;
import org.example.lafiresversin2.sirene.sirenerepository.SireneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SireneDeactivateService {

    private static final Logger logger = LoggerFactory.getLogger(SireneDeactivateService.class);

    private final SireneRepository sireneRepository;
    private final FireRepository fireRepository;

    public SireneDeactivateService(SireneRepository sireneRepository, FireRepository fireRepository) {
        this.sireneRepository = sireneRepository;
        this.fireRepository = fireRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void deactivateSirenerWithoutFire() {
        logger.info("Scheduled task: deactivateSirenerWithoutFire kører...");

        try {
            int deactivatedSirens = sireneRepository.bulkDeactivateSirensWithoutOpenFires();
            logger.info("Antal sirener deaktiveret: " + deactivatedSirens);
        } catch (Exception e) {
            logger.error("Fejl ved deaktivering af sirener", e);
        }
    }


}

