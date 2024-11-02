import javax.persistence.*;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tb_funcionario")
public class Funcionario extends Usuario {
    @Column(nullable = false)
    private int idFunc;

    public Funcionario() {}

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
}
