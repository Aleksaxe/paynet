package uz.paynet.test.util;

import uz.paynet.test.model.Building;
import uz.paynet.test.model.dto.BuildingDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingUtil {
    public static BuildingDto createDto(Building building) {
        return BuildingDto.builder()
                .Id(building.getId())
                .address(building.getAddress())
                .active(building.isActive())
                .build();
    }

    public static List<BuildingDto> createDto(Collection<Building> buildings) {
        return buildings.stream()
                .map(BuildingUtil::createDto)
                .collect(Collectors.toList());
    }
}
