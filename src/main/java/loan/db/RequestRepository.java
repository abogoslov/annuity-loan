package loan.db;

import loan.model.Request;
import org.springframework.data.repository.CrudRepository;

/**
 * @author A.Bogoslov
 */
public interface RequestRepository extends CrudRepository<Request, Integer> {
}
