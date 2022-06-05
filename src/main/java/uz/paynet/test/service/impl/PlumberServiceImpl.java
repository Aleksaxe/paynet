package uz.paynet.test.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.Building;
import uz.paynet.test.model.HttpModels.PlumberRequest;
import uz.paynet.test.model.Plumber;
import uz.paynet.test.model.dto.PlumberDto;
import uz.paynet.test.repository.BuildingRepository;
import uz.paynet.test.repository.PlumberRepository;
import uz.paynet.test.service.PlumberService;
import uz.paynet.test.util.PlumberUtil;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlumberServiceImpl implements PlumberService {
    PlumberRepository plumberRepository;
    BuildingRepository buildingRepository;

    public void savePlumber(PlumberRequest request) {
        Plumber.PlumberBuilder plumberBuilder = Plumber.builder()
                .fullName(request.getFullName())
                .active(true);
        plumberRepository.save(plumberBuilder.build());
    }

    @Override
    public PlumberDto findById(long plumberId) throws ApiException {
        Optional<Plumber> plumber = plumberRepository.findById(plumberId);
        if (plumber.isEmpty()) {
            throw new ApiException("plumber not found");
        }
        return PlumberUtil.createDto(plumber.get());
    }

    @Override
    public List<PlumberDto> findAll() {
        return PlumberUtil.createDto(plumberRepository.findAll());
    }

    @Override
    public void fire(long plumberId) {
        buildingRepository.removeFromAllBuildings(plumberId);
        plumberRepository.fire(plumberId);
    }

    @Override
    public PlumberDto getPlumberByBuildingId(long buildingId) throws ApiException {
        Plumber plumberByBuilding = plumberRepository.getPlumberByBuildingId(buildingId);
        if (plumberByBuilding == null) {
            throw new ApiException("plumber not found");
        }
        return PlumberUtil.createDto(plumberByBuilding);
    }

    @Override
    @Transactional
    public void attach(long plumberId, long buildingId) throws ApiException {
        Optional<Plumber> plumberOptional = plumberRepository.findById(plumberId);
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        if (plumberOptional.isEmpty() || buildingOptional.isEmpty()) {
            throw new ApiException("wrong ids");
        }
        Plumber plumber = plumberOptional.get();
        List<Building> buildings = plumber.getBuildings();
        if (buildings.size() == 4) {
            throw new ApiException("max buildings reached");
        }
        Building building = buildingOptional.get();
        buildings.add(building);
        plumber.setBuildings(buildings);
        building.setPlumber(plumber);
    }
}
