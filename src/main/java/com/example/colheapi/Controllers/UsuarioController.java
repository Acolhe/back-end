package com.example.colheapi.Controllers;
import com.example.colheapi.Classes.ApiResponse;
import com.example.colheapi.Classes.Usuario;
import com.example.colheapi.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/acolhe/usuario")
public class UsuarioController {

    public final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/inserirUsuario")
    public ApiResponse<String> inserirUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario user : usuarios) {
            if (user.getEmail().equals(usuario.getEmail())) {
                return new ApiResponse<>("Alguém já está usando esse email");
            }
        }
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        usuario.setDataCadastro(sqlDate);
        usuario.setDataultimologin(sqlDate);
        usuarioRepository.save(usuario);
        return new ApiResponse<>("Usuário inserido com sucesso");
    }

    @PutMapping("/alterarCadastro/{id}")
    public ApiResponse<String> atualizarCadastro(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setSaldo(usuarioAtualizado.getSaldo());
            usuario.setDiasConsecutivos(usuario.getDiasConsecutivos());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setCodSkinPrincipal(usuarioAtualizado.getCodSkinPrincipal());
            usuario.setPremium(usuarioAtualizado.isPremium());
            usuarioRepository.save(usuario);

            return new ApiResponse<>("Usuário com o id "+ id +" Alterado com sucesso");
        } else {
            return new ApiResponse<>("Usuário com o id " + id + " não encontrado");
        }
    }

    @PutMapping("/assinarPlano/{id}")
    public ApiResponse<String> assinarPlano(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);

        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            if (usuario.isPremium()) {
                return new ApiResponse<>("O usuário " + id + " já é premium.");
            } else {
                usuario.setPremium(!usuario.isPremium());
                usuarioRepository.save(usuario);
                return new ApiResponse<>("Usuário " + id + " AGORA é premium");
            }
        } else {
            return new ApiResponse<>("Usuário não encontrado");
        }
    }

    @GetMapping("saldo/{id}")
    public ApiResponse<String> retornarSaldo(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            String saldoMessage = "O saldo do usuário é: " + usuario.getSaldo();
            return new ApiResponse<>(saldoMessage);
        } else {
            return new ApiResponse<>("Usuário não encontrado");
        }
    }

    @GetMapping("/vertodos")
    public List<Usuario> getAllUsuarioClinicas() {
        return usuarioRepository.findAll();
    }

    @GetMapping("byEmailSenha/{email}/{senha}")
    public Usuario getByEmail(@PathVariable String email, @PathVariable String senha) {
        List<Usuario> usuarios = usuarioRepository.findbyEmailSenha(email, senha);
        Calendar calendar = Calendar.getInstance();
        Calendar calendarData = Calendar.getInstance();
        Date dataAtual = new Date();
        if(!usuarios.isEmpty()){
            Usuario usuario = usuarios.get(0);
            calendarData.setTime(dataAtual);
            calendar.setTime(usuario.getDataultimologin());
            int diaAtual = calendarData.get(Calendar.DAY_OF_MONTH);
            int mesAtual = calendarData.get(Calendar.MONTH);
            int anoAtual = calendarData.get(Calendar.YEAR);
            int diaUsuario = calendar.get(Calendar.DAY_OF_MONTH);
            int mesUsuario = calendar.get(Calendar.MONTH);
            int anoUsuario = calendar.get(Calendar.YEAR);
            int verficaOfensiva = diaAtual - diaUsuario;
            if(diaAtual == diaUsuario && mesAtual == mesUsuario && anoAtual == anoUsuario){
                System.out.println("Mesmo dia");
            } else if (mesAtual == mesUsuario && anoAtual == anoUsuario && verficaOfensiva == 1) {
                usuario.setDiasConsecutivos(usuario.getDiasConsecutivos() + 1);
                System.out.println(verficaOfensiva);
                System.out.println("Mais um de ofensiva");
            }else {
                usuario.setDiasConsecutivos(0);
                System.out.println(verficaOfensiva);
                System.out.println("zera ofensiva");
            }
            usuario.setDataultimologin(new Date());
            usuarioRepository.save(usuario);
            return usuario;
        }else{
            return null;
        }
    }

    @GetMapping("ofensiva/{id}")
    public ApiResponse<String> retornarOfensiva(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            String ofensivaMessage = "As ofensivas do usuário são: " + usuario.getDiasConsecutivos();
            return new ApiResponse<>(ofensivaMessage);
        } else {
            return new ApiResponse<>("Usuário não encontrado");
        }
    }

    @PutMapping("aumentarSaldo/{id}/{valor}")
    public ApiResponse<String> aumentarSaldo(@PathVariable Long id, @PathVariable int valor){
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if(usuarioAlterado.isPresent()){
            Usuario usuario = usuarioAlterado.get();
            usuario.setSaldo(usuario.getSaldo()+valor);
            String retorno = "O novo saldo do usuario é: " + usuario.getSaldo();
            usuarioRepository.save(usuario);
            return new ApiResponse<>(retorno);
        }else{
            return new ApiResponse<>("Usuário não encontrado");
        }
    }
}