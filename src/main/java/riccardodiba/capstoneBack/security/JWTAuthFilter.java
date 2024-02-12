package riccardodiba.capstoneBack.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import riccardodiba.capstoneBack.entities.User;
import riccardodiba.capstoneBack.exception.UnauthorizedException;
import riccardodiba.capstoneBack.services.UsersService;

import java.io.IOException;
import java.util.UUID;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UsersService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Please insert a valid bearer token");
        } else {
            String token = authHeader.substring(7);
            jwtTools.verifyToken(token);
            String id = jwtTools.extractIdFromToken(token);
            User currentUser = userService.findById(UUID.fromString(id));
            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        return servletPath.startsWith("/auth/") ||  servletPath.startsWith("/uploadTown") || servletPath.startsWith("/uploadProvince") || servletPath.equals("/v3/api-docs.yaml") || servletPath.equals("/swagger-ui/index.html") ;
    }
}
