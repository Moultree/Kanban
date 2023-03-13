package com.example.kanban.DTO;

public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String email;

    public UserDTO(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }
}
