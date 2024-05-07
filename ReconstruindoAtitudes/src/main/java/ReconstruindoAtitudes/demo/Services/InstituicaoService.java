package ReconstruindoAtitudes.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ReconstruindoAtitudes.demo.Dtos.InstituicaoDto;
import ReconstruindoAtitudes.demo.Models.InstituicaoModel;
import ReconstruindoAtitudes.demo.Repositories.InstituicaoRepositorie;

@Service
public class InstituicaoService {
    
    @Autowired
    private InstituicaoRepositorie _instituicaoRepositorie;

    public InstituicaoModel cadastrarInstituicao(InstituicaoDto data) {
        InstituicaoModel instituicao = new InstituicaoModel();
        instituicao.setNome(data.Nome());
        instituicao.setSenha(data.Senha());
        instituicao.setCnpj(data.cnpj());

        InstituicaoModel instituicaoResult = _instituicaoRepositorie.save(instituicao);
        return instituicaoResult;
    }

    public List<InstituicaoModel> Instituicoes(){
        return _instituicaoRepositorie.findAll();
    }

    public Optional<InstituicaoModel> instituicaoPorId(Long id){
        Optional<InstituicaoModel> instituicao = _instituicaoRepositorie.findById(id);
        return instituicao;
    }
}

