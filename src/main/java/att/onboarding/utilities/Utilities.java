package att.onboarding.utilities;

import att.onboarding.model.Employee;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

public class Utilities {

    public Date getDate() {

        Long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        return date;
    }

    public String tokenToSubject (String jwtToken){
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            //Verifies token using the same secret
            try {
                String token = jwtToken.substring("Bearer ".length());
                //TODO make utility class with consistent secret for all filters
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String email = decodedJWT.getSubject();
                return email;
            } catch (Exception exception) {
                //TODO User not found
            }
        }
        return null;
    }

}
