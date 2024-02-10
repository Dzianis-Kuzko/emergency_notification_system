package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.MessageCreateDTO;
import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IMessageService;
import by.pet_project.ens.service.factory.MessageServiceFactory;
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

@WebServlet(urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {
    private final IMessageService messageService;
    private final ObjectMapper objectMapper;

    public MessageServlet() {
        this.messageService = MessageServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        PrintWriter writer = resp.getWriter();

        if (userDTO != null) {
            List<MessageDTO> messageDTOList = messageService.get();
            writer.write(objectMapper.writeValueAsString(messageDTOList));
        } else {
            writer.write("Нет прав");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int fromUserId = ((UserDTO) session.getAttribute("user")).getId();

        MessageCreateDTO messageCreateDTO = objectMapper.readValue(req.getInputStream(), MessageCreateDTO.class);
        messageCreateDTO.setFromUserId(fromUserId);
        messageService.create(messageCreateDTO);
    }
}
