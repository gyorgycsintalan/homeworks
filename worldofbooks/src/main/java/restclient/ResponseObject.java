package restclient;

import java.util.List;
import java.util.Map;

public class ResponseObject {
    private int statusCode;
    private String message;
    private Map<String, List<String>> headers;
    private String content;

    public ResponseObject() {
    }

    public ResponseObject(int statusCode, String message, Map<String, List<String>> headers, String content) {
        this.statusCode = statusCode;
        this.message = message;
        this.headers = headers;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
