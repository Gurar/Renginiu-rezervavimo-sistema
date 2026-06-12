package lt.viko.eif.eventsystem.dto;

import java.util.List;
import java.util.Map;

public class SignupResponse {
    private Long userId;

    private List<Map<String, String>> links;

    public Long getUserId() {
        return userId;
    }

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }
}
