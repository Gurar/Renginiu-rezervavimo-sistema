package lt.viko.eif.eventsystem.client.response;

import java.util.List;

public class VenueEmbeddedResponse {
    private List<VenueResponse> venues;

    public List<VenueResponse> getVenues() {
        return venues;
    }

    public void setVenues(List<VenueResponse> venues) {
        this.venues = venues;
    }
}
