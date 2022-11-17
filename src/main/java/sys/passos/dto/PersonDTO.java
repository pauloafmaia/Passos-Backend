package sys.passos.dto;

import lombok.Data;

@Data
public class PersonDTO {

    private Long id;
    private String name;
    private String birthDate;
    private String gender;
    private String email;
    private String address;
    private String city;
    private String state;
    private String country;
}
