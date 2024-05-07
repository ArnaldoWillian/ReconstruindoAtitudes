package ReconstruindoAtitudes.demo.Models;

import java.util.List;

import javax.print.DocFlavor.STRING;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ReconstruindoAtitudes.demo.Dtos.InstituicaoDto;
import ReconstruindoAtitudes.demo.Dtos.USerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Instituicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Nome;

    private String Senha;

    private String cnpj;

    @OneToMany(mappedBy = "instituicao") // Nome do campo na entidade de usuário que representa a relação
    private List<UserModel> usuarios;

    public InstituicaoModel(InstituicaoDto data) {
        Nome = data.Nome();
        Senha = data.Senha();
        this.cnpj = data.cnpj();
    }   
}

