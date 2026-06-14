package lt.viko.eif.eventsystem.client;

import lt.viko.eif.eventsystem.client.response.TicketmasterEvent;
import lt.viko.eif.eventsystem.client.response.TicketmasterResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketmasterClient {
    private final RestClient restClient;

    @Value("${ticketmaster.api.key}")
    private String apiKey;

    @Value("${ticketmaster.root.url}")
    private String rootUrl;

    public TicketmasterClient(RestClient restClient) {

        this.restClient = restClient;
    }

    public TicketmasterResponse getEvents() {


        try {
            return restClient
                    .get()
                    .uri(
                            rootUrl
                            + "/events.json?apikey="
                            + apiKey
                            + "&size=100"
                            + "&segmentName=Music"
                            + "&countryCode=US"
                            + "&sort=date,asc"

                    )
                    .retrieve()
                    .body(TicketmasterResponse.class);

        } catch (RestClientException e) {
            throw new RestClientException("Nepavyko gauti duomenų iš Ticketmaster API", e);
        }
    }

    public TicketmasterEvent getEventById( String eventId) {
        try {
            return restClient
                    .get()
                    .uri(
                            rootUrl
                            + "/events/"
                            + eventId
                            + ".json?apikey="
                            + apiKey

                    )
                    .retrieve()
                    .body(TicketmasterEvent.class);

        } catch (RestClientException e) {
            throw new RestClientException("Nepavyko gauti duomenų iš Ticketmaster API", e);
        }
    }
}
