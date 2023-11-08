package com.example.colheapi.Controllers;
import com.example.colheapi.Classes.ApiResponse;
import com.example.colheapi.Classes.HumorDiario;
import com.example.colheapi.Classes.Usuario;
import com.example.colheapi.Classes.UsuarioComHumoresDTO;
import com.example.colheapi.Repositories.HumorDiarioRepository;
import com.example.colheapi.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acolhe/usuario")
public class UsuarioController {

    public final UsuarioRepository usuarioRepository;
    private HumorDiarioRepository humorDiarioRepository;

    @Autowired
    public void HumorDiarioController(HumorDiarioRepository humorDiarioRepository) {
        this.humorDiarioRepository = humorDiarioRepository;
    }

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, HumorDiarioRepository humorDiarioRepository){
        this.usuarioRepository = usuarioRepository;
        this.humorDiarioRepository = humorDiarioRepository;
    }

    @PostMapping("/inserirUsuario")
    public ResponseEntity<ApiResponse<String>> inserirUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario user : usuarios) {
            if (user.getEmail().equals(usuario.getEmail())) {
                return ResponseEntity.badRequest().body(new ApiResponse<>("Alguém já está usando esse email", null));
            }
        }
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        usuario.setDataCadastro(sqlDate);
        usuario.setDataultimologin(sqlDate);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new ApiResponse<>("Usuário inserido com sucesso", null));
    }

    @PutMapping("/alterarCadastro/{id}")
    public ResponseEntity<ApiResponse<String>> atualizarCadastro(@PathVariable Long id, @RequestBody String nome) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            usuario.setNome(nome);
            usuarioRepository.save(usuario);

            return ResponseEntity.ok(new ApiResponse<>("Usuário com o id "+ id +" Alterado com sucesso", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário com o id " + id + " não encontrado", null));
        }
    }

    @PutMapping("/assinarPlano/{id}")
    public ResponseEntity<ApiResponse<String>> assinarPlano(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);

        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            if (usuario.isPremium()) {
                return ResponseEntity.badRequest().body(new ApiResponse<>("O usuário " + id + " já é premium.", null));
            } else {
                usuario.setPremium(!usuario.isPremium());
                usuarioRepository.save(usuario);
                return ResponseEntity.ok(new ApiResponse<>("Usuário " + id + " AGORA é premium", null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário não encontrado", null));
        }
    }
    @PutMapping("/alterarCodSkinUsuario/{id}/{codSkinUsuario}")
    public ResponseEntity<ApiResponse<String>> atualizarCodSkinUsuario(@PathVariable Long id, @PathVariable int codSkinUsuario) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);

        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            usuario.setCodSkinPrincipal(codSkinUsuario);
            usuarioRepository.save(usuario);

            return ResponseEntity.ok(new ApiResponse<>("Código de skin do usuário com o ID " + id + " alterado com sucesso", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário com o ID " + id + " não encontrado", null));
        }
    }

    @GetMapping("saldo/{id}")
    public ResponseEntity<ApiResponse<String>> retornarSaldo(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            String saldoMessage = "O saldo do usuário é: " + usuario.getSaldo();
            return ResponseEntity.ok(new ApiResponse<>(saldoMessage, null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário não encontrado", null));
        }
    }

    @GetMapping("/vertodos")
    public List<Usuario> getAllUsuarioClinicas() {
        return usuarioRepository.findAll();
    }

    @GetMapping("byEmailSenha/{email}/{senha}")
    public ResponseEntity<UsuarioComHumoresDTO> getByEmail(@PathVariable String email, @PathVariable String senha) {
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
            List<HumorDiario> humores = humorDiarioRepository.findByCodUsuario(usuario.getId());
            UsuarioComHumoresDTO usuarioHumorDTO = new UsuarioComHumoresDTO(usuario, humores);
            return ResponseEntity.ok(usuarioHumorDTO);
        }else{
            return null;
        }
    }

    @GetMapping("ofensiva/{id}")
    public ResponseEntity<ApiResponse<String>> retornarOfensiva(@PathVariable Long id) {
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if (usuarioAlterado.isPresent()) {
            Usuario usuario = usuarioAlterado.get();
            String ofensivaMessage = "As ofensivas do usuário são: " + usuario.getDiasConsecutivos();
            return ResponseEntity.ok(new ApiResponse<>(ofensivaMessage, null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário não encontrado", null));
        }
    }

    @PutMapping("aumentarSaldo/{id}/{valor}")
    public ResponseEntity<ApiResponse<String>> aumentarSaldo(@PathVariable Long id, @PathVariable int valor){
        Optional<Usuario> usuarioAlterado = usuarioRepository.findById(id);
        if(usuarioAlterado.isPresent()){
            Usuario usuario = usuarioAlterado.get();
            usuario.setSaldo(usuario.getSaldo()+valor);
            String retorno = "O novo saldo do usuario é: " + usuario.getSaldo();
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(new ApiResponse<>(retorno, null));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Usuário não encontrado", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioComHumoresDTO>> encontrarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioEncontrado = usuario.get();
            List<HumorDiario> humores = humorDiarioRepository.findByCodUsuario(id);
            UsuarioComHumoresDTO usuarioHumorDTO = new UsuarioComHumoresDTO(usuarioEncontrado, humores);
            return ResponseEntity.ok(new ApiResponse<>("Usuário encontrado com sucesso", usuarioHumorDTO));
        } else {
            return ResponseEntity.ok(new ApiResponse<>("Usuário não encontrado", null));
        }
    }
}
