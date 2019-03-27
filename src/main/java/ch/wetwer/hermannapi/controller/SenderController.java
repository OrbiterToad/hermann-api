package ch.wetwer.hermannapi.controller;

import ch.wetwer.hermannapi.service.ClientService;
import ch.wetwer.hermannapi.service.MessageService;
import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.repository.ClientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.controller
 * @created 27.03.2019
 **/

@CrossOrigin
@RestController
@RequestMapping("out")
public class SenderController {

    private ClientRepository clientRepository;

    private ClientService clientService;
    private MessageService messageService;

    public SenderController(ClientRepository clientRepository, ClientService clientService, MessageService messageService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.messageService = messageService;
    }

    @GetMapping(value = "{pcName}", produces = "application/json")
    public String getCommand(@PathVariable String pcName) {
        return clientRepository.findClientByName(pcName).getCommand();
    }

    @PostMapping(value = "{clientId}", produces = "application/json")
    public Boolean sendCommand(@PathVariable Long clientId, @RequestParam("command") String command) {
        Client client = clientService.saveCommand(clientId, command);
        messageService.saveMessage(command, client);
        return true;
    }
}
