package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.MessageTemplateCreateDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IMessageTemplateService;
import by.pet_project.ens.service.factory.MessageTemplateServiceFactory;
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
    private static final String TEXT_PARAM_NAME = "text";

    private final IMessageTemplateService messageTemplateService;

    public MessageTemplateServlet() {
        this.messageTemplateService = MessageTemplateServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        PrintWriter writer = resp.getWriter();

        if(userDTO!=null){
            List<MessageTemplateDTO> messageTemplateDTOs = messageTemplateService.getUserMessages(userDTO.getId());
            messageTemplateDTOs.forEach(m -> {
                writer.write(m.getId() + ", " + m.getText() + ", " + m.getCreationTimestamp() +", "+ m.getCreatedByUserWithID() + "</br>");
            });
        }else {
            writer.write("Нет прав");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter(TEXT_PARAM_NAME);
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        MessageTemplateCreateDTO messageTemplateCreateDTO = new MessageTemplateCreateDTO(text, userDTO.getId());

        messageTemplateService.create(messageTemplateCreateDTO);
    }
}
