package uz.paynet.test.service;

import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.HttpModels.PlumberRequest;
import uz.paynet.test.model.dto.BuildingDto;
import uz.paynet.test.model.dto.PlumberDto;

import java.util.List;

public interface PlumberService {
    void savePlumber(PlumberRequest request);

    PlumberDto getPlumberByBuildingId(long buildingId) throws ApiException;

    PlumberDto findById(long plumberId) throws ApiException;

    List<PlumberDto> findAll();

    void fire(long plumberId);

    void attach(long plumberId, long buildingId) throws ApiException;
}
