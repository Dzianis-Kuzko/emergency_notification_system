package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.RecipientCreateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IRecipientService;
import by.pet_project.ens.service.factory.RecipientServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/recipient")

public class RecipientServlet extends HttpServlet {
    private final IRecipientService recipientService;
    private final ObjectMapper objectMapper;

    public RecipientServlet() {
        this.recipientService = RecipientServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<RecipientDTO> recipients = this.recipientService.get();
        writer.write(objectMapper.writeValueAsString(recipients));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        RecipientCreateDTO dto = objectMapper.readValue(req.getInputStream(), RecipientCreateDTO.class);
        dto.setCreatedByUserWithID(userDTO.getId());
        this.recipientService.create(dto);

    }
}
