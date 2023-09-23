package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;

	public String generarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(apiSecret);
			String token = JWT.create()
					.withIssuer("voll med")
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withExpiresAt(generarFechaInspiracion())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException();
		}
	}
	
	private Instant generarFechaInspiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}

}




