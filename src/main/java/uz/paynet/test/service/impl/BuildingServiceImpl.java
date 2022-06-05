package uz.paynet.test.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.paynet.test.model.Building;
import uz.paynet.test.model.HttpModels.BuildingRequest;
import uz.paynet.test.model.Plumber;
import uz.paynet.test.model.dto.BuildingDto;
import uz.paynet.test.repository.BuildingRepository;
import uz.paynet.test.repository.PlumberRepository;
import uz.paynet.test.service.BuildingService;
import uz.paynet.test.util.BuildingUtil;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    BuildingRepository buildingRepository;
    PlumberRepository plumberRepository;


    @Override
    public List<BuildingDto> findBuildingsByPlumberId(long plumberId) {
        return BuildingUtil.createDto(buildingRepository.getBuildingsByPlumberId(plumberId));
    }

    @Override
    public List<BuildingDto> findAll() {
        return BuildingUtil.createDto(buildingRepository.findAll());
    }

    @Override
    @Transactional
    public void save(BuildingRequest request) {
        buildingRepository.save(Building.builder()
                .address(request.getAddress())
                .build());
    }

    @Override
    @Transactional
    public void delete(long buildingId) {
        Optional<Building> buildingById = buildingRepository.findById(buildingId);
        buildingById.ifPresent(building -> {
            Plumber plumber = building.getPlumber();
            plumber.getBuildings().remove(building);
            building.setActive(false);
            building.setPlumber(null);
            buildingRepository.save(building);
        });
    }

}
