package com.arrendadora.controller;

import com.arrendadora.model.Usuario;
import com.arrendadora.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.arrendadora.config.JwtUtil;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ Se añadió la inyección de PasswordEncoder

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        // Validar si el email ya está registrado
        if (usuarioService.existeUsuarioPorEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(null); // El email ya está registrado
        }
    
        // Encriptar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    
        // Registrar el usuario
        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuario);
    
        // Retornar la respuesta con el usuario guardado
        return ResponseEntity.status(201).body(usuarioGuardado);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioService.existeUsuarioPorEmail(usuario.getEmail());

        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioBD = usuarioEncontrado.get(); 
            System.out.println("Contraseña ingresada: " + usuario.getPassword());
            System.out.println("Contraseña encriptada en la BD: " + usuarioBD.getPassword());
            boolean passwordMatch = passwordEncoder.matches(usuario.getPassword(), usuarioBD.getPassword());
        System.out.println("Las contraseñas coinciden?" + passwordMatch);

        if (passwordMatch) {
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(token);  // Retorna el token correctamente
        } else {
            return ResponseEntity.status(401).body("Credenciales de contrasena incorrectas");  // Contraseña incorrecta
        }
    } else {
        return ResponseEntity.status(401).body("Credenciales de email incorrectas");  // Email no encontrado
    }
    }
}
