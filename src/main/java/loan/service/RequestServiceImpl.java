package loan.service;

import com.google.common.collect.Lists;
import loan.Utils;
import loan.db.RequestRepository;
import loan.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author A.Bogoslov
 */

@Service
@Repository
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    @Autowired
    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createRequest(Request request) {
        request.setMonthlyCharge(Utils.calcMonthlyCharge(request));
        repository.save(request);
    }

    @Override
    public List<Request> listRequests() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Request getRequest(int id) {
        return repository.findOne(id);
    }

    @Override
    public void updateRequest(Request request) {
        request.setMonthlyCharge(Utils.calcMonthlyCharge(request));
        repository.save(request);
    }

    @Override
    public void removeRequest(int id) {
        repository.delete(id);
    }
}
