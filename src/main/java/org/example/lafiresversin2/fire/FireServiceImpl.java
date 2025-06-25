package org.example.lafiresversin2.fire;


import jakarta.transaction.Transactional;
import org.example.lafiresversin2.sirene.SirenStatus;
import org.example.lafiresversin2.sirene.Sirene;
import org.example.lafiresversin2.sirene.SireneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.awt.geom.Point2D.distance;

@Service
public class FireServiceImpl implements FireService{

    @Autowired
    private FireRepository fireRepository;

    @Autowired
    private SireneRepository sireneRepository;


    public FireServiceImpl(FireRepository fireRepository, SireneRepository sireneRepository) {
        this.fireRepository = fireRepository;
        this.sireneRepository = sireneRepository;
    }

    @Override
    @Transactional
    public FireDTO createFire(FireDTO fireDTO) {
        Fire fire = FireMapper.toEntity(fireDTO);

        //set timestamp
        if (fire.getTimestamp() == null) {
            fire.setTimestamp(java.time.LocalDateTime.now());
        }

        //Find all sirens within 10 km and activate through multiple steps
        List<Sirene> allSirens = sireneRepository.findAll();

        for (Sirene sirene : allSirens) {
            double dist = distance(
                    fire.getLatitude(), fire.getLongitude(),
                    sirene.getLatitude(), sirene.getLongitude()
            );

            if (dist <= 10) {
                sirene.setStatus(SirenStatus.ACTIVE);
                sirene.getFires().add(fire);
                fire.getSirenes().add(sirene);
            }
        }

        fireRepository.save(fire);
        sireneRepository.saveAll(fire.getSirenes());

        return FireMapper.toDTO(fire);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Jordens radius i km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
