package ReconstruindoAtitudes.demo.Services.Validacoes;

import ReconstruindoAtitudes.demo.Dtos.USerDto;
import ReconstruindoAtitudes.demo.Models.UsuarioTipo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationUser {

    public <T> ResponseEntity<ResponseApi<T>> validationUserDto(USerDto data) {

        if (data.Email().isEmpty() || data.Email() == null) {
            return ResponseApi.error("A campo email deve ser preenchido!");
        }
        if (data.Senha().isEmpty() || data.Senha() == null) {
            return ResponseApi.error("O campo Senha deve ser preenchido1");
        }
        if (data.usuarioTipo() != UsuarioTipo.MENTOR && data.usuarioTipo() != UsuarioTipo.AGRESSOR) {
            return ResponseApi.error("O campo userType deve ser preenchido com tipo MENTOR ou AGRESSOR!");
        }

        return ResponseApi.success("Dados validados com sucesso!", null);
    }
}