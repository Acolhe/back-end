    package com.example.colheapi.Controllers;


    import com.example.colheapi.Classes.HumorDiario;
    import com.example.colheapi.Classes.Usuario;
    import com.example.colheapi.Repositories.UsuarioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<String> inserirUsuario(@RequestBody Usuario usuario){
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuario inserido com sucesso");
        }

        //Na hora de logar retorna  tudo
        @PutMapping("/alterarCadastro/{id}")
        public ResponseEntity<String> atualizarCadastro(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);

            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setDataCadastro(usuarioAtualizado.getDataCadastro());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setImagem(usuarioAtualizado.getImagem());
                usuario.setCodSkinPrincipal(usuarioAtualizado.getCodSkinPrincipal());
                usuario.setDiasConsecutivos(usuarioAtualizado.getDiasConsecutivos());
                usuario.setSaldo(usuarioAtualizado.getSaldo());
                usuario.setSenha(usuarioAtualizado.getSenha());
                usuario.setPremium(usuarioAtualizado.isPremium());
                usuario.setTelefone(usuarioAtualizado.getTelefone());
                usuarioRepository.save(usuario);
                return ResponseEntity.ok("Usuario Alterado");

            }else{
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/assinarPlano/{id}")
        public ResponseEntity<String> assinarPlano(@PathVariable Long id){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);


            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                if(usuario.isPremium() == true){
                    return ResponseEntity.badRequest().body("O usuário já é premium.");
                }else {
                    usuario.setPremium(!usuario.isPremium());
                    usuarioRepository.save(usuario);
                    return ResponseEntity.ok("Usuario Alterado");
                }

            }else{
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("saldo/{id}")
        public ResponseEntity<String> retornarSaldo(@PathVariable Long id){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                String retorno = "O saldo do usuário é: " + usuario.getSaldo();
                return ResponseEntity.ok(retorno);

            }else{
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("ofensiva/{id}")
        public ResponseEntity<String> retornarOfensiva(@PathVariable Long id){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                String retorno = "As ofensivas do usuário são: " + usuario.getDiasConsecutivos();
                return ResponseEntity.ok(retorno);

            }else{
                return ResponseEntity.notFound().build();
            }
        }

    }
