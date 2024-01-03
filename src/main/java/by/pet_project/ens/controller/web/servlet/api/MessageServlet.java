package by.pet_project.ens.controller.web.servlet.api;

import by.pet_project.ens.core.dto.MessageCreateDTO;
import by.pet_project.ens.core.dto.MessageDTO;
import by.pet_project.ens.core.dto.MessageTemplateDTO;
import by.pet_project.ens.core.dto.UserDTO;
import by.pet_project.ens.service.api.IMessageService;
import by.pet_project.ens.service.factory.MessageServiceFactory;
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

    public MessageServlet() {
        this.messageService = MessageServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        PrintWriter writer = resp.getWriter();

        if(userDTO!=null){
            List<MessageDTO> messageDTOList = messageService.get();
            messageDTOList.forEach(m -> {
                writer.write(m.getId() + ", " + m.getFromUserId() + ", " + m.getCreationTimestamp() +", "+ m.getMessageTemplateId() + "</br>");
                writer.write("[");
                for (Integer toUserId: m.getToUsersId()){
                    writer.write(toUserId + ", ");
                }
                writer.write("]");
            });
        }else {
            writer.write("Нет прав");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int messageTemplateId = Integer.parseInt(req.getParameter("messageTemplateId"));
        HttpSession session = req.getSession();
        int fromUserId = ((UserDTO)session.getAttribute("user")).getId();

        MessageCreateDTO messageCreateDTO = new MessageCreateDTO(messageTemplateId,fromUserId);
        messageService.create(messageCreateDTO);
    }
}
