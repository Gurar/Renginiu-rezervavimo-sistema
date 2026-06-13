package lt.viko.eif.eventsystem.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketmasterEvent {
    private String id;
    private String name;
    private String info;
    private List<ImageResponse> images;
    private DateResponse dates;
    private List<PriceResponse> priceRanges;
    @JsonProperty("_embedded")
    private VenueEmbeddedResponse _embedded;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public List<ImageResponse> getImages() {
        return images;
    }

    public DateResponse getDates() {
        return dates;
    }

    public List<PriceResponse> getPriceRanges() {
        return priceRanges;
    }

    public VenueEmbeddedResponse get_embedded() {
        return _embedded;
    }

}
