package uz.paynet.test.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.paynet.test.exception.ApiException;
import uz.paynet.test.model.HttpModels.BuildingRequest;
import uz.paynet.test.model.dto.BuildingDto;
import uz.paynet.test.service.BuildingService;
import uz.paynet.test.service.PlumberService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController(value = "/building")
@RequestMapping("/building")
@AllArgsConstructor
@ApiOperation("Building service")
public class BuildingController {
    private final BuildingService buildingService;
    private final PlumberService plumberService;

    @ApiOperation("Get all buildings")
    @GetMapping(value = "/buildings")
    public ResponseEntity<List<BuildingDto>> buildings() {
        return ResponseEntity.ok().body(buildingService.findAll());
    }

    @ApiOperation("Get info about building plumber")
    @GetMapping(value = "/plumber")
    public ResponseEntity getBuildingPlumber(@NotNull long buildingId) {
        try {
            return ResponseEntity.ok().body(plumberService.getPlumberByBuildingId(buildingId));
        } catch (ApiException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @ApiOperation("Add new building")
    @PostMapping
    public ResponseEntity addBuilding(BuildingRequest request) {
        try {
            buildingService.save(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation("delete building")
    @DeleteMapping
    public ResponseEntity deleteBuilding(long buildingId) {
        buildingService.delete(buildingId);
        return ResponseEntity.ok().build();
    }
}
