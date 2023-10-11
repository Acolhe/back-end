package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.ApiResponse;
import com.example.colheapi.Classes.Clinica;
import com.example.colheapi.Classes.Usuario;
import com.example.colheapi.Classes.UsuarioClinica;
import com.example.colheapi.Repositories.ClinicaRepository;
import com.example.colheapi.Repositories.UsuarioClinicaRepository;
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
    public ResponseEntity<ApiResponse<String>> addUsuarioClinica(@RequestBody UsuarioClinica usuarioClinica) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioClinica.getUsuario());
        Optional<Clinica> clinicaOptional = clinicaRepository.findById(usuarioClinica.getClinica());

        if (usuarioOptional.isPresent() && clinicaOptional.isPresent()) {
            Date dataAvaliacao = usuarioClinica.getDataAvaliacao();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataAvaliacao);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date novaDataAvaliacao = calendar.getTime();

            usuarioClinica.setDataAvaliacao(novaDataAvaliacao);

            boolean usuarioClinicaJaRegistrado = usuarioClinicaRepository.existsByUsuarioAndClinica(usuarioClinica.getUsuario(), usuarioClinica.getClinica());
            if (usuarioClinicaJaRegistrado) {
                ApiResponse<String> errorResponse = new ApiResponse<>("Avaliação para essa clínica já registrada por esse usuário");
                return ResponseEntity.badRequest().body(errorResponse);
            } else {
                usuarioClinicaRepository.save(usuarioClinica);
                ApiResponse<String> successResponse = new ApiResponse<>("Avaliação inserida com sucesso");
                return ResponseEntity.ok(successResponse);
            }
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Usuário ou clínica não encontrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/deletarUsuarioClinica/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUsuarioClinica(@PathVariable Long id) {
        if (usuarioClinicaRepository.existsById(id)) {
            usuarioClinicaRepository.deleteById(id);
            ApiResponse<String> successResponse = new ApiResponse<>("Avaliação excluída com sucesso");
            return ResponseEntity.ok(successResponse);
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Avaliação não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
