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

    @PostMapping("/inserirHumor/{idUsuario}")
    public ResponseEntity<ApiResponse<String>> inserirHumor(@PathVariable Long idUsuario, @RequestBody HumorDiario humorDiario) {
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
                return ResponseEntity.badRequest().body(new ApiResponse<>("Humor já registrado hoje"));
            } else {
                humorDiarioRepository.save(humorDiario);
                return ResponseEntity.ok(new ApiResponse<>("Humor inserido com sucesso"));
            }
        } else {
            ApiResponse errorResponse = new ApiResponse("Usuário não encontrado com o ID: " + idUsuario);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



    @DeleteMapping("/excluirHumor/{id}")
    public ResponseEntity<ApiResponse<String>> excluirHumor(@PathVariable Long id) {
        if (humorDiarioRepository.existsById(id)) {
            humorDiarioRepository.deleteById(id);
            ApiResponse<String> successResponse = new ApiResponse<>("Humor excluído com sucesso");
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Humor não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    @GetMapping("/consultarHumor")
    public ResponseEntity<ApiResponse<HumorDiario>> consultarHumorPorIdEData(
            @RequestParam Long idUser,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
        Optional<HumorDiario> humorOptional = humorDiarioRepository.findByCodUsuarioAndData(idUser, data);

        if (humorOptional.isPresent()) {
            ApiResponse<HumorDiario> successResponse = new ApiResponse<>("Humor consultado com sucesso");
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<HumorDiario> errorResponse = new ApiResponse<>("Humor não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
