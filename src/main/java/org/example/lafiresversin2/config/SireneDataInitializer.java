package org.example.lafiresversin2.config;


import jakarta.annotation.PostConstruct;
import org.example.lafiresversin2.sirene.SirenStatus;
import org.example.lafiresversin2.sirene.Sirene;
import org.example.lafiresversin2.sirene.SireneRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class SireneDataInitializer {

    private final SireneRepository sireneRepository;

    public SireneDataInitializer(SireneRepository sireneRepository) {
        this.sireneRepository = sireneRepository;
    }

    @PostConstruct
    public void init() {
        // Base location
        double baseLat = 55.6761;
        double baseLon = 12.5683;

        // Static fixed coordinates around base location
        double[][] locations = {
                {baseLat, baseLon},
                {55.6770, 12.5690},
                {55.6755, 12.5670},
                {55.6765, 12.5700},
                {55.6775, 12.5660},
                {55.6740, 12.5695},
                {55.6750, 12.5710},
                {55.6768, 12.5650},
                {55.6745, 12.5675},
                {55.6758, 12.5688}
        };

        for (double[] location : locations) {
            Sirene sirene = new Sirene();
            sirene.setLatitude(location[0]);
            sirene.setLongitude(location[1]);
            sirene.setStatus(SirenStatus.IDLE);
            sirene.setFires(new HashSet<>());  // no fires yet
            sireneRepository.save(sirene);
        }

        System.out.println("Initialized 10 Sirenes with static locations around base");
    }





}
