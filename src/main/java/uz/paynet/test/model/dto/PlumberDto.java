package uz.paynet.test.model.dto;

import lombok.Builder;
import lombok.Data;
import uz.paynet.test.model.Building;

import java.util.List;

@Data
@Builder
public class PlumberDto {
    private long Id;
    private String fullName;
    private boolean active;
    private List<Building> buildings;
}
