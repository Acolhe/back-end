package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.HumorDiario;
import com.example.colheapi.Classes.Usuario;
import com.example.colheapi.Repositories.HumorDiarioRepository;
import com.example.colheapi.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<String> inserirHumor(@PathVariable Long idUsuario, @RequestBody HumorDiario humorDiario) {
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
                return ResponseEntity.badRequest().body("Humor já registrado hoje");
            } else {
                humorDiarioRepository.save(humorDiario);
                return ResponseEntity.ok("Humor inserido com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/excluirHumor/{id}")
    public ResponseEntity<String> excluirHumor(@PathVariable Long id) {
        if (humorDiarioRepository.existsById(id)) {
            humorDiarioRepository.deleteById(id);
            return ResponseEntity.ok("Humor excluído com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultarHumor")
    public ResponseEntity<HumorDiario> consultarHumorPorIdEData(
            @RequestParam Long idUser,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
        Optional<HumorDiario> humorOptional = humorDiarioRepository.findByCodUsuarioAndData(idUser, data);

        if (humorOptional.isPresent()) {
            HumorDiario humor = humorOptional.get();
            return ResponseEntity.ok(humor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
