package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.ContactData;
import by.pet_project.ens.core.dto.RecipientCreateDTO;
import by.pet_project.ens.core.dto.RecipientDTO;
import by.pet_project.ens.service.api.IRecipientService;
import by.pet_project.ens.service.factory.RecipientServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            writer.write(r.getId() + ", " + r.getSurname() + ", " + r.getName() + ", " + r.getCountry() + ", "
                    + r.getCity() + ", " + r.getContactData().getPhoneNumber() + ", " + r.getContactData().getEmail()
                    + ", " + r.getContactData().getTelegram() + ", " + r.getContactData().getViber() + "</br>");
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        ContactData contactData = new ContactData();
        if (req.getParameter("email") != null) {
            contactData.setEmail(req.getParameter("email"));
        }
        if (req.getParameter("phoneNumber") != null) {
            contactData.setPhoneNumber(req.getParameter("phoneNumber"));
        }
        if (req.getParameter("telegram") != null) {
            contactData.setTelegram(req.getParameter("telegram"));
        }
        if (req.getParameter("viber") != null) {
            contactData.setViber(req.getParameter("viber"));
        }

        RecipientCreateDTO dto = new RecipientCreateDTO(surname, name, country, city, contactData);
        this.recipientService.create(dto);

    }
}
