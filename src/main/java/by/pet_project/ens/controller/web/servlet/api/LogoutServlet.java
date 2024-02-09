package by.pet_project.ens.controller.web.servlet.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/logout")
public class LogoutServlet extends HttpServlet {
    private static final String SESSION_ATTRIBUTE_NAME = "user";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(SESSION_ATTRIBUTE_NAME);
    }
}
