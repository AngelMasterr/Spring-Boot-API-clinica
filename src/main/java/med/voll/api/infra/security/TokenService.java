package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
	
	public String getSubject(String token) {
		if (token == null) {
			throw new RuntimeException();
		}
		DecodedJWT verifier = null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    verifier = JWT.require(algorithm)
			        .withIssuer("voll med")
			        .build()
			        .verify(token);	
		    verifier.getSubject();
		} catch (JWTVerificationException exception){
			System.out.println(exception.toString());
		}
		
		if (verifier.getSubject() == null) {
			throw new RuntimeException("verifier invalido");
		}
		return verifier.getSubject();
	}
	
	private Instant generarFechaInspiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}












