package lt.viko.eif.eventsystem.client.response;

public class StartResponse {
    private String localDate;

    private String localTime;

    public String getLocalDate() {
        return localDate;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }
}
