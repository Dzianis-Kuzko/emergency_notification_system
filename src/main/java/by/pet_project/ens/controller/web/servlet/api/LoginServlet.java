package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IUserService;
import by.pet_project.ens.service.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/login")

public class LoginServlet extends HttpServlet {
    private final IUserService userService;

    public LoginServlet() {
        this.userService = UserServiceFactory.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String  login = req.getParameter("login");
        String password= req.getParameter("password");
        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession();
        if(userService.authenticate(login, password)){
            UserDTO userDTO = userService.get(login);
            session.setAttribute("user", userDTO);
        }else {
            writer.write("Invalid username or password. Please try again.");
        }


    }

}