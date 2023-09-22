package com.example.colheapi.Controllers;

import com.example.colheapi.Classes.Clinica;
import com.example.colheapi.Repositories.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> adicionarClinica(@RequestBody Clinica clinica) {
        Optional<Clinica> existingClinica = clinicaRepository.findByNmClinica(clinica.getNmClinica());

        if (existingClinica.isPresent()) {
            return ResponseEntity.badRequest().body("A clínica já foi adicionada anteriormente.");
        } else {
            Clinica savedClinica = clinicaRepository.save(clinica);
            return ResponseEntity.ok("Clinica adicionada com sucesso!");
        }
    }


    @PutMapping("/alterar/{id}")
    public Clinica alterarClinica(@PathVariable Long id, @RequestBody Clinica clinica) {
        Optional<Clinica> existingClinica = clinicaRepository.findById(id);
        if (existingClinica.isPresent()) {
            Clinica updatedClinica = existingClinica.get();
            updatedClinica.setNmClinica(clinica.getNmClinica());
            updatedClinica.setDescricao(clinica.getDescricao());
            updatedClinica.setImagem(clinica.getImagem());
            updatedClinica.setBairro(clinica.getBairro());
            updatedClinica.setCidade(clinica.getCidade());
            updatedClinica.setNmEstado(clinica.getNmEstado());
            updatedClinica.setSgEstado(clinica.getSgEstado());
            updatedClinica.setPatrocinada(clinica.getPatrocinada());
            return clinicaRepository.save(updatedClinica);
        } else {
            throw new RuntimeException("Clinica não encontrada com o ID: " + id);
        }
    }

    @GetMapping("/{id}")
    public Clinica buscarClinicaPorId(@PathVariable Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinica não encontrada com o ID: " + id));
    }

    @GetMapping("/todas")
    public List<Clinica> buscarTodasAsClinicas() {
        return clinicaRepository.findAll();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirClinica(@PathVariable Long id) {
        if (clinicaRepository.existsById(id)) {
            clinicaRepository.deleteById(id);
            return ResponseEntity.ok("Clinica excluída com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Clinica não encontrada com o ID: " + id);
        }
    }
}
