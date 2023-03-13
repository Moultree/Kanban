package com.example.kanban.DTO;

public class CredentialsDTO {
    private String principal;
    private char[] credentials;


    public CredentialsDTO(String principal, char[] credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public char[] getCredentials() {
        return credentials;
    }
}
