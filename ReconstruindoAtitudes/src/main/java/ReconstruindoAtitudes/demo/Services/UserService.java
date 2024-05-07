package ReconstruindoAtitudes.demo.Services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import ReconstruindoAtitudes.demo.Dtos.USerDto;
import ReconstruindoAtitudes.demo.Models.InstituicaoModel;
import ReconstruindoAtitudes.demo.Models.UserModel;
import ReconstruindoAtitudes.demo.Models.UsuarioTipo;
import ReconstruindoAtitudes.demo.Repositories.InstituicaoRepositorie;
import ReconstruindoAtitudes.demo.Repositories.UserRepositorie;
import ReconstruindoAtitudes.demo.Services.Validacoes.ResponseApi;
import ReconstruindoAtitudes.demo.Services.Validacoes.ValidationUser;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepositorie _UserRepositorie;

    @Autowired
    private ValidationUser validationUser;

    @Autowired
    private InstituicaoService _InstituicaoService;

    @Autowired
    private InstituicaoRepositorie _InstituicaoRepositorie;

    @Transactional
    public UserModel cadastrarUsuario(USerDto data) {
      UserModel usuario = new UserModel(data);

    InstituicaoModel instituicao = _InstituicaoRepositorie.findById(data.instituicaoId())
    .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
    usuario.setInstituicao(instituicao);

    UserModel user = _UserRepositorie.save(usuario);
    return user;
}

    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class TipoUsuarioInvalidoException extends RuntimeException {
        public TipoUsuarioInvalidoException(String message) {
            super(message);
        }
    }

    public List<UserModel> TodosUsuarios(){
        List<UserModel> result = this._UserRepositorie.findAll();
        return result;
    }

    public Optional EncontrarUsuarioPorId(Long id){
        Optional user = this._UserRepositorie.findById(id);
        return user;
    }

    @Transactional
    public UserModel AtualizarUsuario(USerDto data, Long id){
        Optional<UserModel> existeUsuario = this.EncontrarUsuarioPorId(id);
        if(!existeUsuario.isPresent()){
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        UserModel usuarioAtualizar = existeUsuario.get();
        if (data != null && data.Email() != null && !data.Email().isEmpty()) {
            usuarioAtualizar.setEmail(data.Email());
        }
        if (data != null && data.Senha() != null && !data.Senha().isEmpty()) {
            usuarioAtualizar.setSenha(data.Senha());
        }
        if (data != null && data.usuarioTipo() != null) {
            usuarioAtualizar.setUsuarioTipo(data.usuarioTipo());
        }
        return usuarioAtualizar;
    }
    
    public void DeletarUsuario(Long id){
        Optional<UserModel> existeUsuario = this.EncontrarUsuarioPorId(id);
        if(!existeUsuario.isPresent()){
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        _UserRepositorie.deleteById(id);
    }
}
