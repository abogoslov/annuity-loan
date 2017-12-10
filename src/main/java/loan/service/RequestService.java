package loan.service;

import loan.model.Request;

import java.util.List;

/**
 * @author A.Bogoslov
 */
public interface RequestService {

    //create
    void createRequest(Request request);

    //read
    List<Request> listRequests();
    Request getRequest(int id);

    //update
    void updateRequest(Request request);

    //delete
    void removeRequest(int id);
}
