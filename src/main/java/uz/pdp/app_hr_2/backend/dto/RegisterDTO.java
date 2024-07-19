package uz.pdp.app_hr_2.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @Size(min = 3, max = 20)
    private String first_name;

    @Size(min = 5, max = 20)
    private String last_name;


    @NotNull
    private String email;

    @NotNull
    private String password;
}
