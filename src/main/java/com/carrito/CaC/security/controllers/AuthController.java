package com.carrito.CaC.security.controllers;

import com.carrito.CaC.entities.Message;
import com.carrito.CaC.security.dtos.LoginUser;
import com.carrito.CaC.security.dtos.NewUser;
import com.carrito.CaC.security.entities.Role;
import com.carrito.CaC.security.entities.User;
import com.carrito.CaC.security.enums.RoleList;
import com.carrito.CaC.security.jwt.JwtProvider;
import com.carrito.CaC.security.services.RoleService;
import com.carrito.CaC.security.services.UserService;
import com.carrito.CaC.security.util.CookieUtil;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
            UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpServletResponse httpServletResponse,
            @Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult){
        if(bidBindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateToken(authentication);
            CookieUtil.create(httpServletResponse, cookieName, jwt, true, -1, "dev-store-demo.firebaseapp.com");
                return new ResponseEntity<>(new Message("Sesión iniciada"), HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Object> resgister(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUserName(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        if (newUser.getRoles().contains("admin24154545154545aADASKskjdka****/"))
            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesión"), HttpStatus.CREATED);
    }
    @GetMapping("/details")
    public ResponseEntity<Object> getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        Optional<User> user= this.userService.getByUserName(userName);
        if (!user.isPresent())
            return new ResponseEntity<>(new Message("No encotrado"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(), HttpStatus.OK) ;
    }
    @GetMapping("/logout")
    public ResponseEntity<Message> logOut(HttpServletResponse httpServletResponse){
        CookieUtil.clear(httpServletResponse,cookieName);
        return new ResponseEntity<>(new Message("Sesión cerrada"), HttpStatus.OK) ;
    }
    
}
