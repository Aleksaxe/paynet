package uz.paynet.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.Building;
import uz.paynet.test.model.HttpModels.PlumberRequest;
import uz.paynet.test.model.Plumber;
import uz.paynet.test.model.dto.PlumberDto;
import uz.paynet.test.repository.BuildingRepository;
import uz.paynet.test.repository.PlumberRepository;
import uz.paynet.test.service.PlumberService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class PlumberServiceTest {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    PlumberService plumberService;
    @Autowired
    PlumberRepository plumberRepository;

    @BeforeEach
    void init() {
        Building address = Building.builder()
                .Id(1L)
                .address("Address")
                .active(true)
                .build();
        Plumber plumber = Plumber.builder()
                .Id(1L)
                .buildings(Collections.singletonList(address))
                .fullName("Plumber")
                .build();
        buildingRepository.save(address);
        plumberRepository.save(plumber);
    }

    @Test
    @Transactional
    void savePlumber() {
        plumberService.savePlumber(PlumberRequest.builder()
                .fullName("Test name")
                .build());
        List<Plumber> all = plumberRepository.findAll();
        Assertions.assertEquals(2, all.size());
    }

    @Test
    void findExistingPlumber() {
        plumberService.savePlumber(PlumberRequest.builder().fullName("Some plumber").build());
        Assertions.assertFalse(plumberRepository.findAll().isEmpty());
    }

    @Test
    void findNotExistingPlumber() {
        try {
            plumberService.findById(2L);
            fail();
        } catch (ApiException ignored) {
        }
    }

    @Test
    void attachExist() throws ApiException {
        plumberService.attach(1L, 1L);
        Optional<Plumber> plumber = plumberRepository.findById(1L);
        Assertions.assertTrue(plumber.isPresent());
        Assertions.assertEquals(plumber.get().getBuildings().get(0).getId(), 1L);
    }

    @Test
    void attachMoreThanAvailable() {
        try {
            for (int i = 1; i < 10; i++) {
                buildingRepository.save(Building.builder()
                        .Id(i)
                        .address("Address")
                        .active(true)
                        .build());

                plumberService.attach(1L, i);
            }
            fail();
        } catch (ApiException ignored) {

        }
    }
}
