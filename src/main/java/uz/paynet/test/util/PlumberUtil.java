package uz.paynet.test.util;

import uz.paynet.test.model.Plumber;
import uz.paynet.test.model.dto.PlumberDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlumberUtil {
   public static PlumberDto createDto(Plumber plumber) {
        return PlumberDto.builder()
                .Id(plumber.getId())
                .fullName(plumber.getFullName())
                .active(plumber.isActive())
                .build();
    }

    public static List<PlumberDto> createDto(List<Plumber> plumbers) {
        return plumbers.stream()
                .map(PlumberUtil::createDto)
                .collect(Collectors.toList());
    }
}
