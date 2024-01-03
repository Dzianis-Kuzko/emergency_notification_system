package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.Contact;
import by.pet_project.ens.core.dto.RecipientCreateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IRecipientService;
import by.pet_project.ens.service.factory.RecipientServiceFactory;
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

    public RecipientServlet() {
        this.recipientService = RecipientServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        List<RecipientDTO> recipients = this.recipientService.get();
        recipients.forEach(r -> {
            writer.write(r.getId() + ", " + r.getLastName() + ", " + r.getFirstName() + ", " + r.getCountry() + ", "
                    + r.getCity() + ", " + r.getContact().getPhoneNumber() + ", " + r.getContact().getEmail()
                    + ", " + r.getContact().getTelegram() + ", " + r.getContact().getViber() + ", "
                    + r.getCreatedByUserWithID() + "</br>");
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        Contact contact = new Contact();
        if (req.getParameter("email") != null) {
            contact.setEmail(req.getParameter("email"));
        }
        if (req.getParameter("phoneNumber") != null) {
            contact.setPhoneNumber(req.getParameter("phoneNumber"));
        }
        if (req.getParameter("telegram") != null) {
            contact.setTelegram(req.getParameter("telegram"));
        }
        if (req.getParameter("viber") != null) {
            contact.setViber(req.getParameter("viber"));
        }

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        RecipientCreateDTO dto = new RecipientCreateDTO(firstName, lastName, country, city, contact, userDTO.getId());
        this.recipientService.create(dto);

    }
}
