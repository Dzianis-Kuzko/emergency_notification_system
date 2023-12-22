package by.pet_project.ens.web.api;

import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.core.dto.Role;
import by.pet_project.ens.core.dto.UserCreateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IUserService;
import by.pet_project.ens.service.factory.UserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/api/user")

public class UserServlet extends HttpServlet {

    private final IUserService userService;

    public UserServlet() {
        this.userService = UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        List<UserDTO> recipients = this.userService.get();
        recipients.forEach(u -> {
            writer.write(u.getId() + ", " + u.getLogin() + ", " + u.getPassword() + ", " + u.getFirstName() + ", "
                    + u.getMiddleName() + ", " + u.getLastName() + ", " + u.getBirthday()
                    + ", " + u.getRegistrationDateTime() + ", " + u.getRole() + "</br>");
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        Role role = Role.valueOf(req.getParameter("role"));

        UserCreateDTO dto = new UserCreateDTO(login, password, firstName, middleName, lastName, birthday, role);
        this.userService.create(dto);
    }
}
