package ReconstruindoAtitudes.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ReconstruindoAtitudes.demo.Dtos.InstituicaoDto;
import ReconstruindoAtitudes.demo.Models.InstituicaoModel;
import ReconstruindoAtitudes.demo.Models.UserModel;
import ReconstruindoAtitudes.demo.Services.InstituicaoService;
import jakarta.validation.Valid;

@Controller
@RestController
public class InstituicaoController {
    
    @Autowired
    private InstituicaoService _InstituicaoService;

    @PostMapping("/instituicao/cadastrar")
    public ResponseEntity<InstituicaoModel> CadastrarInstituicao(@RequestBody @Valid InstituicaoDto data){
        try {
            InstituicaoModel instituicao = _InstituicaoService.cadastrarInstituicao(data);
            return new ResponseEntity<>(HttpStatus.OK).ok(instituicao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/instituicoes")
    public ResponseEntity<List<InstituicaoModel>> ListarInstituicoes(){
        try {
            List<InstituicaoModel> ListInstituicao = this._InstituicaoService.Instituicoes();
            return ResponseEntity.ok(ListInstituicao); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
    @GetMapping("/instituicoes/{id}")
    public ResponseEntity<Optional<InstituicaoModel>> ListarInstituicoes(@PathVariable(value = "id")  Long id ){
        try {
            Optional<InstituicaoModel> ListInstituicao = this._InstituicaoService.instituicaoPorId(id);
            return ResponseEntity.ok(ListInstituicao); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
}
