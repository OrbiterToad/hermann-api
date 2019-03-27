package ch.wetwer.hermannapi.controller;

import ch.wetwer.hermannapi.service.ClientService;
import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.repository.ClientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("client")
public class ClientController {

    private ClientRepository clientRepository;

    private ClientService clientService;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping(produces = "application/json")
    public List<Client> getClients() {
        return clientRepository.findClientsByOrderByLastseenDesc();
    }

    @GetMapping(value = "{clientId}", produces = "application/json")
    public Client getOneClient(@PathVariable Long clientId) {
        return clientRepository.findClientById(clientId);
    }

    @PostMapping(value = "{clientId}/nickname", produces = "application/json")
    public Boolean setNickname(@PathVariable Long clientId, @RequestParam("nickname") String nickname) {
        clientService.setNickname(clientId, nickname);
        return true;
    }
}
