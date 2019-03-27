package ch.wetwer.hermannapi.service;

import ch.wetwer.hermannapi.data.Client;
import ch.wetwer.hermannapi.data.repository.ClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.Service
 * @created 27.03.2019
 **/

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveCommand(Long clientId, String command) {
        Client client = clientRepository.findClientById(clientId);
        client.setCommand(command);
        clientRepository.save(client);
        return client;
    }

    public Client checkClientName(String pcName) {
        if (clientRepository.existsClientByName(pcName))
            return clientRepository.findClientByName(pcName);
        else {
            Client client = new Client();
            client.setName(pcName);
            client.setNickname(pcName);
            clientRepository.save(client);
            return client;
        }
    }

    public void setNickname( Long clientId, String nickname) {
        Client client = clientRepository.findClientById(clientId);
        client.setNickname(nickname);
        clientRepository.save(client);
    }
}
