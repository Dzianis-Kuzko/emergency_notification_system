package by.pet_project.ens.controller.web.filter;

import by.pet_project.ens.core.dto.Role;
import by.pet_project.ens.core.dto.UserDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/api/user"})
public class AdminSecurityFilter implements Filter {
    private static final String SESSION_ATTRIBUTE_NAME = "user";
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(SESSION_ATTRIBUTE_NAME);

        if ((session != null) && (userDTO != null) && (userDTO.getRole() == Role.ADMIN)) {
            chain.doFilter(request, response);
        } else {
            res.sendError(401);
        }
    }
}
