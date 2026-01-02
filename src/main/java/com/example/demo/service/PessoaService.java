package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.repository.PessoaRepository;
import com.example.demo.models.Pessoa;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio!");
        }
        if (pessoa.getIdade() < 0) {
            throw new IllegalArgumentException("Idade não pode ser negativa!");
        }
        if (pessoa.getCidade() == null || pessoa.getCidade().isBlank()) {
            throw new IllegalArgumentException("Cidade não pode ser vazio!");
        }
        if (pessoa.getEstado() == null || pessoa.getEstado().isBlank()) {
            throw new IllegalArgumentException("O estado não pode ser vazio!");
        }
        if (pessoa.getEstado().length() != 2) {
            throw new IllegalArgumentException("O estado deve ter exatamente dois caracteres!");
        }
        if (pessoa.getPais() == null || pessoa.getPais().isBlank()) {
            throw new IllegalArgumentException("O pais não pode ser vazio!");
        }
    }

    public Pessoa criarPessoa(Pessoa pessoa) throws SQLException {
        validarPessoa(pessoa);
        return pessoaRepository.insert(pessoa);
    }

    public List<Pessoa> listarPessoas() throws SQLException {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(UUID id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser vazio ou nulo!");
        }
        return pessoaRepository.findById(id);
    }

    public boolean atualizarPessoa(UUID id, Pessoa pessoa) throws SQLException {
        validarPessoa(pessoa);
        return pessoaRepository.update(id, pessoa);
    }

    public boolean deletarPessoa(UUID id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser vazio ou nulo!");
        }
        return pessoaRepository.delete(id);
    }
}
