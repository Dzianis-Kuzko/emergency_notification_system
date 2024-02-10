package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.UserCreateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IUserService;
import by.pet_project.ens.service.factory.UserServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/user")

public class UserServlet extends HttpServlet {

    private final IUserService userService;
    private final ObjectMapper objectMapper;

    public UserServlet() {
        this.userService = UserServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<UserDTO> userDTOS = this.userService.get();
        writer.write(objectMapper.writeValueAsString(userDTOS));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCreateDTO dto = this.objectMapper.readValue(req.getInputStream(), UserCreateDTO.class);

        this.userService.create(dto);
    }
}
