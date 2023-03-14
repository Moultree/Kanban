package com.example.kanban.service;

import com.example.kanban.DTO.CredentialsDTO;
import com.example.kanban.entity.User;
import com.example.kanban.exception.NotFoundException;
import com.example.kanban.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class AuthService {
    //Using HMAC - Hash Message Auth Code
    @Value("${auth.cookie.hmac-key:secret-key}")
    private String secretKey;

    //TODO:finish/fix/refactor EVERYTHING
    private IUserService userService;
    public AuthService(IUserService userService) {
        this.userService = userService;
    }

    public User authenticate(CredentialsDTO credentialDto) {
        //TODO change hardcode
        //return userService.getUserByUsername(credentialDto.getPrincipal());
        return new User("username", "pass", "rofl");
    }
    public User findByUsername(String username){
            //TODO change hardcode
            //return userService.getUserByUsername(credentialDto.getPrincipal());
            return new User("username","pass","rofl");
    }

    public User findByToken(String token){
        String[] parts = token.split("&");
        Long userId = Long.valueOf(parts[0]);
        String username = parts[1];
        String hmac = parts[2];

        User user = findByUsername(username);

        if (!hmac.equals(calculateHmac(user)) || userId != user.getId()){
            throw new RuntimeException("Invalid Cookie value");
        }
        //TODO link them somehow
        return user;
    }
    /*
    Cookie
    <id>&
    <login>&
    <sign>
     */
    public String createToken(User user){
        return user.getId() + "&"
                + user.getUsername() + "&"
                + calculateHmac(user);
    }

    private String calculateHmac(User user){
        byte[] sectetKeyBytes = Objects.requireNonNull(secretKey)
                .getBytes(StandardCharsets.UTF_8);
        byte[] valiueBytes = Objects.requireNonNull(user.getId() + "&" + user.getUsername())
                .getBytes(StandardCharsets.UTF_8);

        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec sec = new SecretKeySpec(sectetKeyBytes, "HmacSHA512");
            mac.init(sec);
            byte[] hmacBytes = mac.doFinal(valiueBytes);
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
