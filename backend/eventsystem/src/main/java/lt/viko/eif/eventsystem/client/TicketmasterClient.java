package lt.viko.eif.eventsystem.client;

import lt.viko.eif.eventsystem.client.response.TicketmasterResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketmasterClient {
    private final RestTemplate restTemplate;

    @Value("${ticketmaster.api.key}")
    private String apiKey;

    @Value("${ticketmaster.root.url}")
    private String rootUrl;

    public TicketmasterClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TicketmasterResponse getEvents() {

//        String url = rootUrl + "/events.json" + "?apikey=" + apiKey + "&size=50" + "&segmentName=Music";
        String url = rootUrl +
                "/events.json" +
                "?apikey=" + apiKey +
                "&size=25" +
                "&segmentName=Music" +
                "&countryCode=US" +
                "&includeTBA=no" +
                "&includeTBD=no" +
                "&sort=date,asc";


        try {
            return restTemplate.getForObject( url, TicketmasterResponse.class );
        } catch (RestClientException e) {
            throw new RestClientException("Nepavyko gauti duomenų iš Ticketmaster API", e);
        }
    }
}
