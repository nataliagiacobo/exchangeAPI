package br.ada.exchangeAPI.controller;

import br.ada.exchangeAPI.controller.dto.LoginRequest;
import br.ada.exchangeAPI.controller.dto.TokenResponse;
import br.ada.exchangeAPI.infra.security.TokenService;
import br.ada.exchangeAPI.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(
                loginRequest.getCpf(),
                loginRequest.getPassword()
        );

        var authentication = authenticationManager.authenticate(authenticate);
        var token = tokenService.tokenGenerate((Customer) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenResponse(token));
    }
}
