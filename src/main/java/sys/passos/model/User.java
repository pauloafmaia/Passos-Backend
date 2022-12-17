package sys.passos.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false)
    private String login;

    @Column(length = 20, nullable = false)
    private String password;
}
