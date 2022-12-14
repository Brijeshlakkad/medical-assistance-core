package com.medicalassistance.core.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private UserDetailsService userDetailsService;
  private JwtTokenUtil jwtTokenUtil;
  private String tokenHeader;
  private String tokenCookieName;


  public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService,
      JwtTokenUtil jwtTokenUtil, String tokenHeader, String tokenCookieName) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.tokenHeader = tokenHeader;
    this.tokenCookieName = tokenCookieName;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    logger.debug("processing authentication for '{}'", request.getRequestURL());

    final String requestHeader = request.getHeader(this.tokenHeader);

    String username = null;
    String authToken = null;
    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
      authToken = requestHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        logger.error("an error occured during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("the token is expired and not valid anymore", e);
      }
    }

    else {
      Cookie[] cookies = request.getCookies();
      boolean foundCookie = false;

      if(cookies == null || cookies.length ==0)
      {
        logger.info("No cookies found either");
      }
      else
      {
        for(Cookie cookie: cookies){
          if(cookie.getName().equalsIgnoreCase(tokenCookieName))
          {
            authToken = cookie.getValue();
            foundCookie = true;
            logger.info("Found token in cookie");
            username = jwtTokenUtil.getUsernameFromToken(authToken);
            break;
          }
        }
      }

      if(!foundCookie)
      {
        logger.debug("couldn't find bearer string, will ignore the header or cookie not found for token" );
      }
    }

    logger.debug("checking authentication for user '{}'", username);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      logger.debug("security context was null, so authorizating user");

      // It is not compelling necessary to load the use details from the database. You could also
      // store the information
      // in the token and read it from it. It's up to you ;)
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      // For simple validation it is completely sufficient to just check the token integrity. You
      // don't have to call
      // the database compellingly. Again it's up to you ;)
      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.debug("authorizated user '{}', setting security context", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }
}

