package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.*;
import com.example.colheapi.Repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acolhe/clinicas")
public class ClinicaController {

    private final ClinicaRepository clinicaRepository;

    @Autowired
    public ClinicaController(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponse<String>> adicionarClinica(@RequestBody Clinica clinica) {
        Optional<Clinica> existingClinica = clinicaRepository.findByNmClinica(clinica.getNmClinica());

        if (existingClinica.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("A clínica já foi adicionada anteriormente", null));
        } else {
            Clinica savedClinica = clinicaRepository.save(clinica);
            return ResponseEntity.ok(new ApiResponse<>("Clínica adicionada com sucesso", null));
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ApiResponse<String>> alterarClinica(@PathVariable Long id, @RequestBody Clinica clinica) {
        Optional<Clinica> existingClinica = clinicaRepository.findById(id);
        if (existingClinica.isPresent()) {
            Clinica updatedClinica = existingClinica.get();
            updatedClinica.setNmClinica(clinica.getNmClinica());
            updatedClinica.setEmail(clinica.getEmail());
            updatedClinica.setTelefone(clinica.getTelefone());
            updatedClinica.setDescricao(clinica.getDescricao());
            updatedClinica.setImagem(clinica.getImagem());
            updatedClinica.setBairro(clinica.getBairro());
            updatedClinica.setCidade(clinica.getCidade());
            updatedClinica.setNmEstado(clinica.getNmEstado());
            updatedClinica.setSgEstado(clinica.getSgEstado());
            updatedClinica.setPatrocinada(clinica.getPatrocinada());
            clinicaRepository.save(updatedClinica);
            return ResponseEntity.ok(new ApiResponse<>("Clínica alterada com sucesso", null));
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Clínica não encontrada com o ID: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Clinica>> buscarClinicaPorId(@PathVariable Long id) {
        Optional<Clinica> clinicaOptional = clinicaRepository.findById(id);

        if (clinicaOptional.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>("Clínica encontrada com sucesso", clinicaOptional.get()));
        } else {
            ApiResponse<Clinica> errorResponse = new ApiResponse<>("Clínica não encontrada com o ID: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/todas")
    public List<Clinica> buscarTodasAsClinicas() {
        return clinicaRepository.findAll();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponse<String>> excluirClinica(@PathVariable Long id) {
        if (clinicaRepository.existsById(id)) {
            clinicaRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("Clínica excluída com sucesso", null));
        } else {
            ApiResponse<String> errorResponse = new ApiResponse<>("Clínica não encontrada com o ID: " + id, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
