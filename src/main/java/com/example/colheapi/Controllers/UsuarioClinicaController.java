package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.Clinica;
import com.example.colheapi.Classes.Usuario;
import com.example.colheapi.Classes.UsuarioClinica;
import com.example.colheapi.Repositories.ClinicaRepository;
import com.example.colheapi.Repositories.UsuarioClinicaRepository;
import com.example.colheapi.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acolhe/usuarioclinicas")
public class UsuarioClinicaController {

    private final UsuarioClinicaRepository usuarioClinicaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired
    public UsuarioClinicaController(UsuarioClinicaRepository usuarioClinicaRepository) {
        this.usuarioClinicaRepository = usuarioClinicaRepository;
    }

    @GetMapping("/vertodos")
    public List<UsuarioClinica> getAllUsuarioClinicas() {
        return usuarioClinicaRepository.findAll();
    }

    @GetMapping("/buscarPorId/{id}")
    public UsuarioClinica buscarClinicaPorId(@PathVariable Long id) {
        return usuarioClinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário da clinica não encontrada com o ID: " + id));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> addUsuarioClinica(@RequestBody UsuarioClinica usuarioClinica) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioClinica.getUsuario());
        Optional<Clinica> clinicaoptional = clinicaRepository.findById(usuarioClinica.getClinica());

        if (usuarioOptional.isPresent() && clinicaoptional.isPresent()) {
            Date dataAvaliacao = usuarioClinica.getDataAvaliacao();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataAvaliacao);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date novaDataHumor = calendar.getTime();


            usuarioClinica.setDataAvaliacao(novaDataHumor);

            boolean usuarioClinicaJaRegistrado = usuarioClinicaRepository.existsByUsuarioAndClinica(usuarioClinica.getUsuario(), usuarioClinica.getClinica());
            if (usuarioClinicaJaRegistrado) {
                return ResponseEntity.badRequest().body("Avaliação para essa clinica já registrada por esse usuário");
            } else {
                usuarioClinicaRepository.save(usuarioClinica);

                return ResponseEntity.ok("Avaliação inserida com sucesso");
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletarUsuarioClinica/{id}")
    public ResponseEntity<String> deleteUsuarioClinica(@PathVariable Long id) {
        if (usuarioClinicaRepository.existsById(id)) {
            usuarioClinicaRepository.deleteById(id);
            return ResponseEntity.ok("Avaliação excluida com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
