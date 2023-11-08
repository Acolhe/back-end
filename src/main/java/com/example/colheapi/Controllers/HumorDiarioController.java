package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.HumorDiario;
import com.example.colheapi.Classes.*;
import com.example.colheapi.Repositories.HumorDiarioRepository;
import com.example.colheapi.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acolhe/humor")
public class HumorDiarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final HumorDiarioRepository humorDiarioRepository;

    @Autowired
    public HumorDiarioController(HumorDiarioRepository humorDiarioRepository) {
        this.humorDiarioRepository = humorDiarioRepository;
    }
    @GetMapping("/todas")
    public List<HumorDiario> buscarTodasAsClinicas() {
        return humorDiarioRepository.findAll();
    }

    @PostMapping("/inserirHumor/{idUsuario}")
    public ResponseEntity<ApiResponse> inserirHumor(@PathVariable Long idUsuario, @RequestBody HumorDiario humorDiario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Date dataHumor = humorDiario.getData();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataHumor);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date novaDataHumor = calendar.getTime();

            humorDiario.setUsuario(usuario);
            humorDiario.setCodUsuario(idUsuario);
            humorDiario.setData(novaDataHumor);

            boolean humorJaRegistrado = humorDiarioRepository.existsByCodUsuarioAndData(idUsuario, novaDataHumor);
            if (humorJaRegistrado) {
                return ResponseEntity.ok(new ApiResponse<>("Humor já registrado hoje", null));
            } else {
                humorDiarioRepository.save(humorDiario);
                Optional<Usuario> usuario1 = usuarioRepository.findById(idUsuario);
                if (usuario1.isPresent()) {
                    Usuario usuarioEncontrado = usuario1.get();
                    List<HumorDiario> humores = humorDiarioRepository.findByCodUsuario(idUsuario);
                    UsuarioComHumoresDTO usuarioHumorDTO = new UsuarioComHumoresDTO(usuarioEncontrado, humores);
                    return ResponseEntity.ok(new ApiResponse<>("Usuário encontrado com sucesso", usuarioHumorDTO));
                } else {
                    return ResponseEntity.ok(new ApiResponse<>("Usuário não encontrado", null));
                }
            }
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Usuário não encontrado com o ID: " + idUsuario, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/excluirHumor/{id}")
    public ResponseEntity<ApiResponse<String>> excluirHumor(@PathVariable Long id) {
        if (humorDiarioRepository.existsById(id)) {
            humorDiarioRepository.deleteById(id);
            ApiResponse<String> successResponse = new ApiResponse<>("Humor excluído com sucesso", null);
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Humor não encontrado", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/consultarHumor")
    public ResponseEntity<ApiResponse<HumorDiario>> consultarHumorPorIdEData(
            @RequestParam Long idUser,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
        Optional<HumorDiario> humorOptional = humorDiarioRepository.findByCodUsuarioAndData(idUser, data);

        if (humorOptional.isPresent()) {
            HumorDiario humor = humorOptional.get();
            ApiResponse<HumorDiario> successResponse = new ApiResponse<>("Humor consultado com sucesso", humor);
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<HumorDiario> errorResponse = new ApiResponse<>("Humor não encontrado", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/todosHumoresPorUsuario/{idUsuario}")
    public ResponseEntity<ApiResponse<List<HumorDiario>>> buscarTodasAsClinicasPorUsuario(@PathVariable Long idUsuario) {
        List<HumorDiario> humores = humorDiarioRepository.findByCodUsuario(idUsuario);

        if (!humores.isEmpty()) {
            ApiResponse<List<HumorDiario>> successResponse = new ApiResponse<>("Humores consultados com sucesso", humores);
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<List<HumorDiario>> errorResponse = new ApiResponse<>("Nenhum humor encontrado para o usuário", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
