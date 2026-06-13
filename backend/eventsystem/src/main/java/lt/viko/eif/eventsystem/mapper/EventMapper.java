package lt.viko.eif.eventsystem.mapper;

import lt.viko.eif.eventsystem.client.response.PriceResponse;
import lt.viko.eif.eventsystem.client.response.TicketmasterEvent;
import lt.viko.eif.eventsystem.dto.EventResponse;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class EventMapper {


    public EventResponse toResponse(TicketmasterEvent event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setTitle(event.getName());
        eventResponse.setDescription(event.getInfo());

        if (event.getDates() != null && event.getDates().getStart() != null)
            eventResponse.setEventDate(event.getDates().getStart().getLocalDate());

        if (event.getImages() != null && !event.getImages().isEmpty())
            eventResponse.setImage(event.getImages().get(0).getUrl());

        if (event.getPriceRanges() != null && !event.getPriceRanges().isEmpty()) {
            PriceResponse price = event.getPriceRanges().get(0);
            eventResponse.setMinPrice(price.getMin());
            eventResponse.setMaxPrice(price.getMax());
            eventResponse.setCurrency(price.getCurrency());
        }

        if (event.get_embedded() != null && event.get_embedded().getVenues() != null && !event.get_embedded().getVenues().isEmpty()) {
            var venue = event.get_embedded().getVenues().get(0);

            if (venue.getAddress() != null)
                eventResponse.setAddress(venue.getAddress().getLine1());

            if (venue.getCountry() != null)
                eventResponse.setCountry(venue.getCountry().getName());
        }

        eventResponse.set_links(
                Map.of(
                        "self",
                        Map.of(
                                "href",
                                "events/" + event.getId()
                        )
                )
        );

        return eventResponse;

    }
}
