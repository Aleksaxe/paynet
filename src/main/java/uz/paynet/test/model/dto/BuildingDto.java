package uz.paynet.test.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildingDto {
    private long Id;
    private String address;
    private boolean active;
    private PlumberDto plumber;
}
