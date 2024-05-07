package ReconstruindoAtitudes.demo.Dtos;

import ReconstruindoAtitudes.demo.Models.InstituicaoModel;
import ReconstruindoAtitudes.demo.Models.UsuarioTipo;

public record USerDto(String Email, String Senha, UsuarioTipo usuarioTipo, Long instituicaoId, String Nome) {
    
}


