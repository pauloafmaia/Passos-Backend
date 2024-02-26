package sys.passos.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String login;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(length = 80, nullable = false)
    private String email;
}
