package sys.passos.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    private String gender;

    @Column(length = 80, nullable = false)
    private String email;

    @OneToOne(mappedBy = "person")
    private User user;

    @OneToMany(mappedBy="person")
    private List<Address> address;
}
