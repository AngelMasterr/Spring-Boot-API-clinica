package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		obtener el token del header
		var token = request.getHeader("Authorization");
		if (token == null || token == "") {
			throw new RuntimeException("El token enviado esta vacio o es nulo");
		}
		token = token.replace("Bearer ", "");
		System.out.println(token);
		System.out.println(tokenService.getSubject(token)); 		
		filterChain.doFilter(request, response);
	}
	
	

}