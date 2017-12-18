package loan.db;

import loan.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author A.Bogoslov
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Client findByFirstNameAndSurnameAndDocId(String firstName, String surname, Long docId);
}
