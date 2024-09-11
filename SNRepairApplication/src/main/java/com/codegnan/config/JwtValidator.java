package com.codegnan.config;

import java.io.IOException;
import java.security.Key;

import javax.crypto.SecretKey;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class JwtValidator  extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if(jwt!=null) {
			jwt=jwt.substring(7);
			try {
				
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				
				Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				String email=String.valueOf(claims.get("email"));
				String autorities=String.valueOf(claims.get("authorities"));
				List<GrantedAuthority> auths =AuthorityUtils.commaSeparatedStringToAuthorityList(autorities);
				Authentication authentication =new UsernamePasswordAuthenticationToken(email,null,auths);
				
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				
				
				
				
			} catch (Exception e) {
				
				throw new BadCredentialsException("Invalid token ..... form jwt vaildator"); 
				
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
//	private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//	
//	 @Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	            throws ServletException, IOException {
//	        
//	        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//	        
//	        if (jwt != null && jwt.startsWith("Bearer ")) {
//	            jwt = jwt.substring(7);
//	            try {
//	                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//	                String email = claims.getSubject();
//	                List<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("authorities")).stream()
//	                        .map(SimpleGrantedAuthority::new)
//	                        .collect(Collectors.toList());
//	                
//	                UserDetails userDetails = User.withUsername(email).authorities(authorities).build();
//	                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                SecurityContextHolder.getContext().setAuthentication(authentication);
//	            } catch (Exception e) {
//	                throw new BadCredentialsException("Invalid token");
//	            }
//	        }
//	        
//	        filterChain.doFilter(request, response);
//	    }
	

}
