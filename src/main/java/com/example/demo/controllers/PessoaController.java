package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.PessoaService;
import com.example.demo.models.Pessoa;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getPessoas() throws SQLException {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable UUID id) throws SQLException {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pessoa> postPessoa(@RequestBody Pessoa pessoa) throws SQLException {
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(201).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPessoa(@PathVariable UUID id, @RequestBody Pessoa pessoa) throws SQLException {
        boolean editado = pessoaService.atualizarPessoa(id, pessoa);
        if (editado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable UUID id) throws SQLException {
        boolean deletado = pessoaService.deletarPessoa(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
