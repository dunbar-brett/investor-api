package applicationRequestObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/request-object")
public class RequestObjectController {

    @GetMapping
    public List<RequestObject> getRequestObjectList() {
        return null;
    }
}
