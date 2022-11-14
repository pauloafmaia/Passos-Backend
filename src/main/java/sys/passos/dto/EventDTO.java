package sys.passos.dto;

import lombok.Data;

@Data
public class EventDTO {

    private Long id;
    private String name;
    private String local;
    private String date;
    private String setList;

}