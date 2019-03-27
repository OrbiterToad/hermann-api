package ch.wetwer.hermannapi.service;

import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.Message;
import ch.wetwer.hermannapi.data.repository.MessageRepository;
import org.springframework.stereotype.Service;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.Service
 * @created 27.03.2019
 **/

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(String command, Client client) {
        Message message = new Message();
        message.setClient(client);
        message.setMessage("> " + command);
        if (command.startsWith("chat")) {
            message.setType("message");
        } else {
            message.setType("command");
        }
        messageRepository.save(message);
        return message;
    }

    public void saveMessage(String type, String value, Client client) {
        Message message = new Message();
        message.setType(type);
        message.setMessage(value);
        message.setClient(client);
        messageRepository.save(message);
    }
}
