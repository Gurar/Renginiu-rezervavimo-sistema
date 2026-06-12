package lt.viko.eif.eventsystem.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketmasterResponse {
    @JsonProperty("_embedded")
    private EmbeddedResponse _embedded;

    public EmbeddedResponse getEmbedded() {
        return _embedded;
    }

    public void setEmbedded(EmbeddedResponse embedded) {
        this._embedded = embedded;
    }
}
