package uz.paynet.test.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.HttpModels.PlumberRequest;
import uz.paynet.test.model.dto.PlumberDto;
import uz.paynet.test.service.BuildingService;
import uz.paynet.test.service.PlumberService;

import java.util.List;

@RestController(value = "/plumber")
@RequestMapping("/plumber")
@ApiOperation("Plumber service")
@AllArgsConstructor
public class PlumberController {
    private final PlumberService plumberService;
    private final BuildingService buildingService;

    @ApiOperation("Get all plumbers")
    @GetMapping(value = "/plumbers")
    public ResponseEntity<List<PlumberDto>> plumbers() {
        return ResponseEntity.ok().body(plumberService.findAll());
    }

    @ApiOperation("Add new plumber")
    @PostMapping
    public ResponseEntity addPlumber(PlumberRequest request) {
        plumberService.savePlumber(request);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("fire plumber")
    @DeleteMapping
    public ResponseEntity firePlumber(Long plumberId) {
        plumberService.fire(plumberId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get all buildings serviced by plumber")
    @GetMapping(path = "/plumber_buildings")
    public ResponseEntity getPlumberBuildings(Long plumberId) {
        return ResponseEntity.ok().body(buildingService.findBuildingsByPlumberId(plumberId));
    }

    @ApiOperation("attach plumber and building")
    @PutMapping(value = "/attach")
    public ResponseEntity attach(long plumberId, long buildingId) {
        try {
            plumberService.attach(plumberId, buildingId);
            return ResponseEntity.ok().build();
        } catch (ApiException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
