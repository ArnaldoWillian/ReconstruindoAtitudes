package ReconstruindoAtitudes.demo.Models;

import javax.print.DocFlavor.STRING;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ReconstruindoAtitudes.demo.Dtos.USerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String Nome;


    @Email(message = "CAMPO E-MAIL INVÁLIDO!")
    private String Email;

    private String Senha;

    @Enumerated(EnumType.STRING)
    private UsuarioTipo usuarioTipo;

    @ManyToOne
    @JoinColumn(name = "instituicao_id") // Nome da coluna na tabela de usuários que armazena o ID da instituição
    @JsonIgnore
    private InstituicaoModel instituicao;

    public UserModel(USerDto data) {
        Email = data.Email();
        Senha = data.Senha();
        usuarioTipo = data.usuarioTipo();
        Nome = data.Nome();
    }
}
