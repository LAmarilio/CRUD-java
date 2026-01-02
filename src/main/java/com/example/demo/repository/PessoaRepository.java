package com.example.demo.repository;

import com.example.demo.config_db.ConnectionFactory;
import com.example.demo.models.Pessoa;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class PessoaRepository {
    private static final Logger logger = Logger.getLogger(PessoaRepository.class.getName());

    public Pessoa insert(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoas (nome, idade, cidade, estado, pais) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id, created_at";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setString(3, pessoa.getCidade());
            stmt.setString(4, pessoa.getEstado());
            stmt.setString(5, pessoa.getPais());

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Falha ao inserir pessoa: nenhum resultado retornado.");
                }
                pessoa.setId((UUID) rs.getObject("id"));
                pessoa.setCreatedAt(rs.getObject("created_at", java.time.OffsetDateTime.class));
            }
            logger.info("Id: " + pessoa.getId() + "\n" + "Criado em: " + pessoa.getCreatedAt());
        }

        return pessoa;
    }

    public boolean delete(UUID id) throws SQLException {
        String sql = "DELETE FROM pessoas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                logger.warning("Nenhuma pessoa encontrada com o ID: " + id);
                return false;
            } else {
                logger.info("Pessoa removida com sucesso! ID: " + id);
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao deletar pessoa: " + e.getMessage());
            throw e;
        }
    }

    public boolean update(UUID id, Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoas " +
                "SET nome = ?, idade = ?, cidade = ?, estado = ?, pais = ? " +
                "WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setString(3, pessoa.getCidade());
            stmt.setString(4, pessoa.getEstado());
            stmt.setString(5, pessoa.getPais());
            stmt.setObject(6, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                logger.warning("Nenhuma pessoa encontrada com o ID: " + id);
                return false;
            } else {
                logger.info("Pessoa com ID " + id + " atualizada com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao editar informações da pessoa: " + e.getMessage());
            throw e;
        }
    }

    public List<Pessoa> findAll() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoas ORDER BY created_at DESC";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pessoa p = new Pessoa(
                        (UUID) rs.getObject("id"),
                        rs.getObject("created_at", java.time.OffsetDateTime.class),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais"));
                pessoas.add(p);
            }
        }
        return pessoas;
    }

    public Pessoa findById(UUID id) throws SQLException {
        String sql = "SELECT * FROM pessoas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException(String.format("Falha ao encontrar pessoa com o id = %s", id));
                }
                Pessoa pessoa = new Pessoa(
                    (UUID) rs.getObject("id"),
                    rs.getObject("created_at", java.time.OffsetDateTime.class),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("pais")
                );
                return pessoa;
            }
        } catch (SQLException e) {
            logger.severe("Erro ao encontrar pessoa pelo id: " + e.getMessage());
            throw e;
        }
    }
}
