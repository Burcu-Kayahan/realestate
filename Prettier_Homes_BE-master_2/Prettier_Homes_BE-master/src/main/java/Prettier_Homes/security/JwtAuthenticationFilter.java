package Prettier_Homes.security;

import Prettier_Homes.config.Exceptions;
import Prettier_Homes.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwtToken = extractJwtFromRequest(request);
			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
				Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
				UserDetails user = userDetailsService.loadUserById(id);
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			else {
//				throw new Exceptions.JwtAuthenticationException("Geçersiz bir token, lütfen yeniden giriş yapın.");
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				response.setContentType("application/json");
//				response.getWriter().write("{\"isTokenValid\": false, \"message\": \"Geçersiz bir token, lütfen yeniden giris yapin.\"}");
//				return;
			}

		} catch(Exception e) {
//			throw new Exceptions.JwtAuthenticationException("Geçersiz bir token, lütfen yeniden giriş yapın.");
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			response.setContentType("application/json");
//			response.getWriter().write("{\"isTokenValid\": false, \"message\": \"Geçersiz bir token, lütfen yeniden giris yapin.\"}");
			return;

		}
		filterChain.doFilter(request, response);
	}
//	protected void doFilterInternal2(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		try {
//			String jwtToken = extractJwtFromRequest(request);
//			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
//				Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
//				UserDetails user = userDetailsService.loadUserById(id);
//				if(user != null) {
//					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//					SecurityContextHolder.getContext().setAuthentication(auth);
//				}
//			}else {
//				// Gecersiz token durumunda istenilen cevabı ayarla
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//				response.setContentType("application/json");
//				response.getWriter().write("{\"success\": false, \"message\": \"Geçersiz bir token, lütfen yeniden giriş yapın.\"}");
//				return;
//			}
//
//		} catch(Exception e) {
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			response.setContentType("application/json");
//			response.getWriter().write("{\"success\": false, \"message\": \"Geçersiz bir token, lütfen yeniden giriş yapın.\"}");
//			return;
//
//		}
//		filterChain.doFilter(request, response);
//	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
			return bearer.substring("Bearer".length() + 1);
		return null;
	}

}
