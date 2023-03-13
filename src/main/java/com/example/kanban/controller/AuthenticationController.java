package com.example.kanban.controller;

import com.example.kanban.entity.User;
import com.example.kanban.exception.InvalidUserException;
import com.example.kanban.security.CookieAuthentificationFilter;
import com.example.kanban.service.AuthService;
import com.example.kanban.service.interfaces.IUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    //TODO link user and DTOs or smth else
    private final IUserService userService;
    private final AuthService authService;

    public AuthenticationController(IUserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<User> signIn(@AuthenticationPrincipal User user,
                                       HttpServletResponse response) {
        Cookie cookie = new Cookie(CookieAuthentificationFilter.COOKIE_NAME, authService.createToken(user) );
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(Duration.of(1, ChronoUnit.DAYS).toSecondsPart());
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody @Valid User user) throws InvalidUserException {
        User createdUser = userService.createUser(user);
        return ResponseEntity.created(URI.create("/users/"+createdUser.getId())).body(createdUser);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> CookieAuthentificationFilter.COOKIE_NAME
                        .equals(cookie.getName()))
                .findFirst();

        authCookie.ifPresent((cookie -> cookie.setMaxAge(0)));

        return ResponseEntity.noContent().build();
    }

}
