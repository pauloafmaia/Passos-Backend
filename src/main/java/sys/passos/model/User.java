package sys.passos.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column( nullable = false)
    private String email;
    @NotNull
    @Column( nullable = false)
    private String password;
    @NotNull
    @Column( nullable = false)
    private String name;
    @NotNull
    @Column( nullable = false)
    private String birth;
    @NotNull
    @Column( nullable = false)
    private String cep;
    @NotNull
    @Column( nullable = false)
    private String address;
    @NotNull
    @Column( nullable = false)
    private String city;
    @NotNull
    @Column( nullable = false)
    private String state;
    @NotNull
    @Column( nullable = false)
    private String phone;
    @NotNull
    @Column( nullable = false)
    private String gender;

}
