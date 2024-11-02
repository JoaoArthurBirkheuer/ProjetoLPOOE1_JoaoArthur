import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(length = 11, nullable = false, unique = true)
    private String CPF;

    @Column(length = 50, nullable = false)
    private String email;

    public Usuario() {}

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
