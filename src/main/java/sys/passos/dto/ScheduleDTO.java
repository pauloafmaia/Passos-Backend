package sys.passos.dto;

import lombok.Data;

@Data
public class ScheduleDTO {

    private Long id;
    private String event;
    private String local;
    private String date;
    private String time;
}
