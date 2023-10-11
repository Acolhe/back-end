package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.*;
import com.example.colheapi.Repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidApplicationException;
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
    public ResponseEntity<ApiResponse> adicionarClinica(@RequestBody Clinica clinica) {
        Optional<Clinica> existingClinica = clinicaRepository.findByNmClinica(clinica.getNmClinica());

        if (existingClinica.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse("A clínica já foi adicionada anteriormente."));
        } else {
            Clinica savedClinica = clinicaRepository.save(clinica);
            return ResponseEntity.ok(new ApiResponse("Clinica adicionada com sucesso!"));
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ApiResponse> alterarClinica(@PathVariable Long id, @RequestBody Clinica clinica) {
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
            return ResponseEntity.ok(new ApiResponse("Clínica alterada com sucesso!"));
        } else {
            ApiResponse errorResponse = new ApiResponse("Clínica não encontrada com o ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> buscarClinicaPorId(@PathVariable Long id) {
        Optional<Clinica> clinicaOptional = clinicaRepository.findById(id);

        if (clinicaOptional.isPresent()) {
            ApiResponse response = new ApiResponse("Clinica encontrada com sucesso");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("Clinica não encontrada com o ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }




    @GetMapping("/todas")
    public List<Clinica> buscarTodasAsClinicas() {
        return clinicaRepository.findAll();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponse> excluirClinica(@PathVariable Long id) {
        if (clinicaRepository.existsById(id)) {
            clinicaRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Clínica excluida com sucesso!"));
        } else {
            ApiResponse errorResponse = new ApiResponse("Clínica não encontrada com o ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
