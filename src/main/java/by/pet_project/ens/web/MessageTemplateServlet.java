package by.pet_project.ens.web;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.service.api.IMessageTemplateService;
import by.pet_project.ens.service.factory.MessageTemplateServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/messageTemplate")

public class MessageTemplateServlet extends HttpServlet {
    private static final String TEXT_PARAM_NAME = "text";

    private final IMessageTemplateService messageTemplateService;

    public MessageTemplateServlet() {
        this.messageTemplateService = MessageTemplateServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        List<MessageTemplateDTO> messageTemplateDTOs = messageTemplateService.get();
        messageTemplateDTOs.forEach(m -> {
            writer.write(m.getId() + ", " + m.getText() + ", " + m.getDate() + "</br>");
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String text = req.getParameter(TEXT_PARAM_NAME);

        MessageTemplateCreateDTO messageTemplateCreateDTO = new MessageTemplateCreateDTO(text);

        messageTemplateService.create(messageTemplateCreateDTO);
    }
}
