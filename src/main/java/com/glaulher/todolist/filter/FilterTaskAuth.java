package com.glaulher.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.glaulher.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  private IUserRepository userRepository;

  public FilterTaskAuth(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (request.getServletPath().startsWith("/tasks/")) {
      var autorization = request.getHeader("Authorization");

      var authEncoded = autorization.substring("Basic".length()).trim();

      byte[] authDecode = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecode);
      String[] credentials = authString.split(":");
      String username = credentials[0];
      String password = credentials[1];

      var user = this.userRepository.findByUsername(username);

      if (user == null) {
        response.sendError(401);
      } else {

        var passwordMatch = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if (passwordMatch.verified) {
          request.setAttribute("idUser", user.getId());

          filterChain.doFilter(request, response);

        } else {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }
}
