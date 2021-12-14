package com.ServerMesagerie.controllers;

import com.ServerMesagerie.models.LoginInformation;
import com.ServerMesagerie.security.JwtTokenUtil;
import com.ServerMesagerie.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController( AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?>  login(@RequestBody LoginInformation userDetails) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                        userDetails.getPassword()));

        return (UserUtils.isAuthenticated(authentication))
                ? ResponseEntity.status(HttpStatus.OK).body(jwtTokenUtil.generateToken(userDetails))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        request.getSession().invalidate();
    }
}
