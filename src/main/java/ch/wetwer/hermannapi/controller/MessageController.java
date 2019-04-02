package ch.wetwer.hermannapi.controller;

import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.Message;
import ch.wetwer.hermannapi.data.repository.ClientRepository;
import ch.wetwer.hermannapi.data.repository.MessageRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.controller
 * @created 27.03.2019
 **/

@CrossOrigin
@RestController
@RequestMapping("api/message")
public class MessageController {

    private MessageRepository messageRepository;
    private ClientRepository clientRepository;

    public MessageController(MessageRepository messageRepository, ClientRepository clientRepository) {
        this.messageRepository = messageRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "{clientId}", produces = "application/json")
    public List<Message> getMessagesForClient(@PathVariable Long clientId) {
        Client client = clientRepository.getOne(clientId);
        return messageRepository.findMessageByClientOrderByIdDesc(client);
    }

    @PostMapping(value = "{clientId}/clear", produces = "application/json")
    public Boolean clearMessages(@PathVariable Long clientId) {
        Client client = clientRepository.getOne(clientId);
        messageRepository.deleteByClient(client);
        return true;
    }
}
