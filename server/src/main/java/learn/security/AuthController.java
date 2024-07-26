package learn.security;
import learn.domain.UserService;
import learn.security.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final UserService service;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserService service) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody Map<String, String> credentials) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("userName"), credentials.get("hashedPassword"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.getTokenFromUser((User) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(@RequestBody /*Map<String, String> credentials*/ learn.models.User user) {
        User appUser = null;

        try {

            List<String> roles = Collections.singletonList("USER");
            ////just need to think if logic to set admin
            user.setRoles(roles);

            service.add(user);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }


        // happy path...

        HashMap<String, Integer> map = new HashMap<>();
        map.put("userId", user.getUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
