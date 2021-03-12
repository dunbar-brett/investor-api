package applicationRequestObject;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// business logic happens here
public class RequestObjectService {

    private final RequestObjectDataAccessService requestObjectDataAccessService;

    @Autowired
    public RequestObjectService(RequestObjectDataAccessService requestObjectDataAccessService) {
        this.requestObjectDataAccessService = requestObjectDataAccessService;
    }

    List<RequestObject> getRequestObjects() {
       return requestObjectDataAccessService.getRequestObjects();
    }

}