package uz.paynet.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.Building;
import uz.paynet.test.model.HttpModels.BuildingRequest;
import uz.paynet.test.model.Plumber;
import uz.paynet.test.repository.BuildingRepository;
import uz.paynet.test.repository.PlumberRepository;
import uz.paynet.test.service.BuildingService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BuildingServiceTest {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    BuildingService buildingService;
    @Autowired
    PlumberRepository plumberRepository;

    @BeforeEach
    void init() {
        Plumber plumber = Plumber.builder()
                .Id(1L)
                .fullName("Plumber")
                .build();
        Building address = Building.builder()
                .Id(1L)
                .address("Address")
                .plumber(plumber)
                .active(true)
                .build();
        plumberRepository.save(plumber);
        buildingRepository.save(address);
    }
    @Test
    void saveBuilding() throws ApiException {
        buildingService.save(BuildingRequest.builder()
                .address("Test address")
                .Id(0)
                .build());
        List<Building> building = buildingRepository.findAll();
        Assertions.assertFalse(building.isEmpty());
        Assertions.assertEquals(building.get(0).getAddress(), "Address");
    }
    @Test
    void delete() {
        buildingService.delete(1L);
        Optional<Building> byId = buildingRepository.findById(1L);
        Assertions.assertTrue(byId.isPresent());
        Assertions.assertFalse(byId.get().isActive());
    }
}
