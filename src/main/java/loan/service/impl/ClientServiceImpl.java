package loan.service.impl;

import loan.db.ClientRepository;
import loan.model.Client;
import loan.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author A.Bogoslov
 */

@Service
@Repository
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClient(int id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client findClient(Client client) {
        return clientRepository.findByFirstNameAndSurnameAndDocId(
                client.getFirstName(),
                client.getSurname(),
                client.getDocId()
        );
    }
}
