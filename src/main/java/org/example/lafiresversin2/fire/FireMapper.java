package org.example.lafiresversin2.fire;

import org.example.lafiresversin2.sirene.SireneDTO;
import org.example.lafiresversin2.sirene.SireneMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class FireMapper {

    public static FireDTO toDTO(Fire fire) {
        Set<SireneDTO> sireneDTOs = fire.getSirenes()
                .stream()
                .map(SireneMapper::toDTO)
                .collect(Collectors.toSet());

        return new FireDTO(
                fire.getFireId(),
                fire.getLatitude(),
                fire.getLongitude(),
                fire.getTimestamp(),
                fire.isClosed(),
                sireneDTOs
        );
    }

    public static Fire toEntity(FireDTO dto) {
        Fire fire = new Fire();
        fire.setFireId(dto.getFireId());
        fire.setLatitude(dto.getLatitude());
        fire.setLongitude(dto.getLongitude());
        fire.setTimestamp(dto.getTimestamp());
        fire.setClosed(dto.isClosed());
        // Sirenes are not set here — typically handled by service/repository logic.
        return fire;
    }

    public static Set<FireDTO> toDTOSet(Set<Fire> fires) {
        return fires.stream()
                .map(FireMapper::toDTO)
                .collect(Collectors.toSet());
    }
}