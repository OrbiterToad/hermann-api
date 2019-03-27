package ch.wetwer.hermannapi.service;

import ch.wetwer.hermannapi.data.repository.ClientRepository;
import org.springframework.stereotype.Service;

/**
 * @author Wetwer
 * @project hermann-api
 * @package ch.wetwer.hermannapi.Service
 * @created 27.03.2019
 **/

@Service
public class ReceiverService {

    private ClientRepository clientRepository;

    public ReceiverService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

}
