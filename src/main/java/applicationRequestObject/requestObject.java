package applicationRequestObject;
import java.util.UUID;

public class requestObject {
    private UUID requestId;
    private String body;
    private String status;

    public requestObject(UUID requestId, String body, String status) {
        this.requestId = requestId;
        this.body = body;
        this.status = status;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
