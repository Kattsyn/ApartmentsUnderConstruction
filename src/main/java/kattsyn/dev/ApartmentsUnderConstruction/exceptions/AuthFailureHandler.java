package kattsyn.dev.ApartmentsUnderConstruction.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        if (exception instanceof AccountDisabledException || exception instanceof InternalAuthenticationServiceException) {
            response.sendRedirect("/login?deactivated");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}
