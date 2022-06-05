package uz.paynet.test.model.HttpModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlumberRequest {
    private long Id;
    @Size(min = 10, message = "name require")
    private String fullName;
}
