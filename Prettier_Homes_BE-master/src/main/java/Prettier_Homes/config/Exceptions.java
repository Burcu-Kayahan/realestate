package Prettier_Homes.config;

import Prettier_Homes.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

//@Server
@Configuration
public class Exceptions {

    public static class CustomRuntimeException extends RuntimeException {

        private boolean result;
        private AuthResponse authResponse;

        public CustomRuntimeException(Exception e) {

        }

//        public CustomRuntimeException(String message, boolean b) {
//        }

        public AuthResponse CustomRuntimeException(Exception e) {
            this.authResponse.setMessage(e.getMessage());
            this.authResponse.setIsAuthenticated(false);
            return authResponse;
        }

    }


    public static class JwtAuthenticationException extends RuntimeException {

        private String message;

        public JwtAuthenticationException(String message) {
            super(message);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
