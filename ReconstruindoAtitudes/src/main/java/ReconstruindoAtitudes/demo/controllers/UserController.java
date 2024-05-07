package ReconstruindoAtitudes.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ReconstruindoAtitudes.demo.Dtos.USerDto;
import ReconstruindoAtitudes.demo.Models.UserModel;
import ReconstruindoAtitudes.demo.Services.UserService;
import ReconstruindoAtitudes.demo.Services.Validacoes.ResponseApi;
import jakarta.validation.Valid;

@RestController
public class UserController {
    
    @Autowired
    private UserService _UserService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody @Valid USerDto data) {
        try {
            UserModel user = _UserService.cadastrarUsuario(data);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> GetTodosUsuarios(){
        try {
            List<UserModel> user = this._UserService.TodosUsuarios();
            return ResponseEntity.ok(user); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> AtualizarUsuario(@RequestBody @Valid USerDto data, @PathVariable(value = "id")  Long id){
        try {
            UserModel UsuarioAtualizado = this._UserService.AtualizarUsuario(data, id);
            return ResponseEntity.ok(UsuarioAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity DeletarUsuario(@PathVariable(value = "id")  Long id){
        try {
            _UserService.DeletarUsuario(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
