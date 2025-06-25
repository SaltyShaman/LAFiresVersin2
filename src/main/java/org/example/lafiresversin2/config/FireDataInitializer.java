package org.example.lafiresversin2.config;
import jakarta.annotation.PostConstruct;
import org.example.lafiresversin2.fire.Fire;
import org.example.lafiresversin2.fire.FireRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class FireDataInitializer {

    private final FireRepository fireRepository;

    public FireDataInitializer(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (fireRepository.count() > 0) {
            return; // Testdata findes allerede
        }


        //Fires W/O sirenes to see if it can work without sirenes.
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

        Fire fire3 = new Fire();
        fire3.setLatitude(55.6780);
        fire3.setLongitude(12.5700);
        fire3.setTimestamp(LocalDateTime.now().minusMinutes(10));
        fire3.setClosed(true);

        fireRepository.save(fire1);
        fireRepository.save(fire2);
        fireRepository.save(fire3);

        System.out.println("Testdata: 3 fires er oprettet");
    }
}
