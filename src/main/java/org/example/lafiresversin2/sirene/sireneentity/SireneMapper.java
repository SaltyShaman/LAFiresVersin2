package org.example.lafiresversin2.sirene.sireneentity;

import org.example.lafiresversin2.fire.fireentity.Fire;

import java.util.Set;
import java.util.stream.Collectors;

public class SireneMapper {

    public static SireneDTO toDTO(Sirene sirene) {
        Set<Long> fireIds = sirene.getFires()
                .stream()
                .map(Fire::getFireId)
                .collect(Collectors.toSet());

        return new SireneDTO(
                sirene.getSireneId(),
                sirene.getLatitude(),
                sirene.getLongitude(),
                sirene.getStatus(),
                fireIds
        );
    }

    public static Sirene toEntity(SireneDTO dto) {
        Sirene sirene = new Sirene();
        sirene.setSireneId(dto.getSireneId());
        sirene.setLatitude(dto.getLatitude());
        sirene.setLongitude(dto.getLongitude());
        sirene.setStatus(dto.getStatus());
        return sirene;
    }

    public static Set<SireneDTO> toDTOSet(Set<Sirene> sirenes) {
        return sirenes.stream()
                .map(SireneMapper::toDTO)
                .collect(Collectors.toSet());
    }
}