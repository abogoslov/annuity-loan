package loan.service;

import loan.model.Client;

/**
 * @author A.Bogoslov
 */
public interface ClientService {

    //create
    void createClient(Client client);

    // read
    Client getClient(int id);
    Client findClient(Client client);
}
