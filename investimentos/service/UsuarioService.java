package com.agibank.investimentos.service;

import com.agibank.investimentos.model.Usuario;

public class UsuarioService {
    public void cadastrarUsuario(String nome, String cpf, String email, String senha) {
        Usuario usuario = new Usuario(nome, cpf, email, senha);
        // Save to database or list
    }

    public Usuario realizarLogin(String email, String senha) {
        // Validate and return user
        return null;
    }
}