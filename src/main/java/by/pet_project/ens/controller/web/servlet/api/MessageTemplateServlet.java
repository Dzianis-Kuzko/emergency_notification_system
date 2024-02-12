package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IMessageTemplateService;
import by.pet_project.ens.service.factory.MessageTemplateServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/api/messageTemplate")

public class MessageTemplateServlet extends HttpServlet {
    private static final String SESSION_ATTRIBUTE_NAME = "user";
    private final IMessageTemplateService messageTemplateService;
    private final ObjectMapper objectMapper;


    public MessageTemplateServlet() {
        this.messageTemplateService = MessageTemplateServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(SESSION_ATTRIBUTE_NAME);

        PrintWriter writer = resp.getWriter();

        List<MessageTemplateDTO> messageTemplateDTOs = messageTemplateService.getUserMessageTemplates(userDTO.getId());
        writer.write(objectMapper.writeValueAsString(messageTemplateDTOs));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(SESSION_ATTRIBUTE_NAME);

        MessageTemplateCreateDTO messageTemplateCreateDTO = objectMapper.readValue(req.getInputStream(), MessageTemplateCreateDTO.class);

        messageTemplateCreateDTO.setCreatedByUserWithID(userDTO.getId());

        messageTemplateService.create(messageTemplateCreateDTO);
    }
}
