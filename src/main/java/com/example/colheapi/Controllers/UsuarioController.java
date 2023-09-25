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
            List<Usuario> usuarios = usuarioRepository.findAll();
            for(Usuario user: usuarios){
                if(user.getEmail().equals( usuario.getEmail())){
                    return ResponseEntity.badRequest().body("Alguém já está usando esse email");
                }
            }
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            usuario.setDataCadastro(sqlDate);
            usuario.setDataultimologin(sqlDate);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuario inserido com sucesso");
        }

        @PutMapping("/alterarCadastro/{id}")
        public ResponseEntity<String> atualizarCadastro(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);

            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setSenha(usuarioAtualizado.getSenha());
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

        @PutMapping("aumentarSaldo/{id}/{valor}")
        public ResponseEntity<String> aumentarSaldo(@PathVariable Long id, @PathVariable int valor){
            Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
            if(usuarioAlterado.isPresent()){
                Usuario usuario = usuarioAlterado.get();
                usuario.setSaldo(usuario.getSaldo()+valor);
                String retorno = "O novo saldo do usuario é: " + usuario.getSaldo();
                return ResponseEntity.ok(retorno);

            }else{
                return ResponseEntity.notFound().build();
            }
        }

    }
