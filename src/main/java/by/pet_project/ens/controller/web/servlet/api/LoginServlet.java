package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IUserService;
import by.pet_project.ens.service.factory.UserServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/api/login")

public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PARAM_NAME = "login";
    private static final String PASSWORD_PARAM_NAME = "password";
    private static final String SESSION_ATTRIBUTE_NAME = "user";
    private final IUserService userService;
    private ObjectMapper objectMapper;

    public LoginServlet() {
        this.userService = UserServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> mapParam = objectMapper.readValue(req.getInputStream(), new TypeReference<>() {
        });

        String login = mapParam.get(LOGIN_PARAM_NAME);
        String password = mapParam.get(PASSWORD_PARAM_NAME);

        HttpSession session = req.getSession();

        UserDTO userDTO = userService.authenticate(login, password);

        if (userDTO != null) {
            session.setAttribute(SESSION_ATTRIBUTE_NAME, userDTO);
        } else {
            resp.sendError(401);
        }


    }

}
