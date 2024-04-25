package mk.ukim.finki.uiktp.bookeshop.config;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.uiktp.bookeshop.model.exceptions.PasswordDoNotMatch;
import mk.ukim.finki.uiktp.bookeshop.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernameAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;

    public CustomUsernameAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder, HttpServletRequest request) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(username.isEmpty() || password.isEmpty()){
            throw new BadCredentialsException("Invalid Credentials");
        }

        UserDetails userDetails = this.userService.findByUsername(username)
                .orElseThrow(()-> new BadCredentialsException("Invalid Credentials"));

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new PasswordDoNotMatch();
        }

        request.getSession().setAttribute("user", userDetails);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}