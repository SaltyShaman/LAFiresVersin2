package org.example.lafiresversin2.config;
import jakarta.annotation.PostConstruct;
import org.example.lafiresversin2.fire.Fire;
import org.example.lafiresversin2.fire.FireRepository;
import org.example.lafiresversin2.sirene.SireneRepository;
import org.example.lafiresversin2.sirene.Sirene;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@DependsOn("sireneDataInitializer")
public class FireDataInitializer {

    private final FireRepository fireRepository;
    private final SireneRepository sireneRepository;

    public FireDataInitializer(FireRepository fireRepository, SireneRepository sireneRepository) {
        this.fireRepository = fireRepository;
        this.sireneRepository = sireneRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (fireRepository.count() > 0) {
            return; // Testdata findes allerede
        }

        //get 2 sirenes from the database
        List<Sirene> sirener = sireneRepository.findAll();
        Sirene sirene1 = sirener.get(0);
        Sirene sirene2 = sirener.get(1);

        Set<Sirene> sharedSireneSet = new HashSet<>();
        sharedSireneSet.add(sirene1);
        sharedSireneSet.add(sirene2);


        //Fires 1-2 W/O sirenes to see if it can work without sirenes.
        Fire fire1 = new Fire();
        fire1.setLatitude(55.6761);
        fire1.setLongitude(12.5683);
        fire1.setTimestamp(LocalDateTime.now().minusMinutes(30));
        fire1.setClosed(false);

        Fire fire2 = new Fire();
        fire2.setLatitude(55.6770);
        fire2.setLongitude(12.5690);
        fire2.setTimestamp(LocalDateTime.now().minusMinutes(20));
        fire2.setClosed(false);


        //Fire 3 + 4 with same sirens (sirens to be added)


        Fire fire3 = new Fire();
        fire3.setLatitude(55.6780);
        fire3.setLongitude(12.5700);
        fire3.setTimestamp(LocalDateTime.now().minusMinutes(10));
        fire3.setClosed(false);
        fire3.setSirenes(sharedSireneSet);


        Fire fire4 = new Fire();
        fire4.setLatitude(55.6790);
        fire4.setLongitude(12.5710);
        fire4.setTimestamp(LocalDateTime.now().minusMinutes(5));
        fire4.setClosed(false);
        fire4.setSirenes(sharedSireneSet);




        fireRepository.save(fire1);
        fireRepository.save(fire2);
        fireRepository.save(fire3);
        fireRepository.save(fire4);

    }
}
