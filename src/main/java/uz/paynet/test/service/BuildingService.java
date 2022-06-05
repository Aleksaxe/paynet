package uz.paynet.test.service;

import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.HttpModels.BuildingRequest;
import uz.paynet.test.model.dto.BuildingDto;
import uz.paynet.test.model.dto.PlumberDto;

import java.util.List;

public interface BuildingService {

    List<BuildingDto> findBuildingsByPlumberId(long plumberId);

    List<BuildingDto> findAll();

    void save(BuildingRequest build) throws ApiException;

    void delete(long buildingId);
}
