package com.bookin.bookin.filter;


import com.bookin.bookin.service.CustomUserDetailService;
import com.bookin.bookin.util.JwtUtil;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (bearerToken != null && bearerToken.startsWith("Bearer")) {
            token = bearerToken.substring(7);

            try {
                username=jwtUtil.extractUsername(token);

                UserDetails userDetails= customUserDetailService.loadUserByUsername(username);

                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
                else{
                    System.out.println("Invalid Token!!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Invalid Bearer Token Format!!");
        }

        filterChain.doFilter(request,response);
    }
}
