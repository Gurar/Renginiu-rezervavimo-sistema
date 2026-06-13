package lt.viko.eif.eventsystem.mapper;

import lt.viko.eif.eventsystem.client.response.*;
import lt.viko.eif.eventsystem.dto.EventResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EventMapperTest {

    private EventMapper eventMapper;
    private TicketmasterEvent event;

    @BeforeEach
    void setUp() {
        eventMapper = new EventMapper();

        event = new TicketmasterEvent();
        event.setId("evt-001");
        event.setName("Rock Concert");
        event.setInfo("An amazing rock concert");
    }


    @Test
    void shouldSetsBasicFields() {
        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getId()).isEqualTo("evt-001");
        assertThat(response.getTitle()).isEqualTo("Rock Concert");
        assertThat(response.getDescription()).isEqualTo("An amazing rock concert");
    }


    @Test
    void  shouldSetsEventDateWhenDatePresent() {
        StartResponse start = new StartResponse();;
        start.setLocalDate("2025-08-15");

        DateResponse dates = new DateResponse();
        dates.setStart(start);

        event.setDates(dates);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getEventDate()).isEqualTo("2025-08-15");
    }

    @Test
    void shouldDoesNoSetEventDateWhenDatesNull() {
        event.setDates(null);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getEventDate()).isNull();
    }

    @Test
    void shouldDoesNotSetEventDateWhenStartNull() {
        DateResponse dates = new DateResponse();
        dates.setStart(null);
        event.setDates(dates);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getEventDate()).isNull();
    }


    @Test
    void shouldSetImageWhenImagesPresent() {
        ImageResponse image = new ImageResponse();
        image.setUrl("https://example.com/image.jpg");
        event.setImages(List.of(image));

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getImage()).isEqualTo("https://example.com/image.jpg");
    }

    @Test
    void shouldDoesNotSetImageWhenImagesEmpty() {
        event.setImages(List.of());

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getImage()).isNull();
    }

    @Test
    void shouldDoesNotSetImageWhenImageNull() {
        event.setImages(null);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getImage()).isNull();
    }

    @Test
    void shouldSetsPriceFieldsWhenPriceRangesPresent() {
        PriceResponse price = new PriceResponse();
        price.setMin(10.0);
        price.setMax(99.0);
        price.setCurrency("EUR");
        event.setPriceRanges(List.of(price));

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getMinPrice()).isEqualTo(10.0);
        assertThat(response.getMaxPrice()).isEqualTo(99.0);
        assertThat(response.getCurrency()).isEqualTo("EUR");
    }

    @Test
    void shouldDoesNotSetPriceWhenPriceRangesEmpty() {
        event.setPriceRanges(List.of());

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getMinPrice()).isNull();
        assertThat(response.getMaxPrice()).isNull();
        assertThat(response.getCurrency()).isNull();
    }

    @Test
    void shouldDoesNotSetPriceWhenPriceRangesNull() {
        event.setPriceRanges(null);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getMinPrice()).isNull();
        assertThat(response.getCurrency()).isNull();
    }

    @Test
    void shouldSetsAddressAndCountryWhenVenuePresent() {
        AddressResponse address = new AddressResponse();
        address.setLine1("Main Street 1");

        CountryResponse country = new CountryResponse();
        country.setName("Lithuania");

        VenueResponse venue = new VenueResponse();
        venue.setAddress(address);
        venue.setCountry(country);

        VenueEmbeddedResponse embedded = new VenueEmbeddedResponse();
        embedded.setVenues(List.of(venue));

        event.set_embedded(embedded);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getAddress()).isEqualTo("Main Street 1");
        assertThat(response.getCountry()).isEqualTo("Lithuania");
    }

    @Test
    void shouldDoesNotSetAddressWhenAddressNull() {
        VenueResponse venue = new VenueResponse();
        venue.setAddress(null);
        venue.setCountry(null);

        VenueEmbeddedResponse embedded = new VenueEmbeddedResponse();
        embedded.setVenues(List.of(venue));

        event.set_embedded(embedded);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getAddress()).isNull();
        assertThat(response.getCountry()).isNull();
    }

    @Test
    void shouldDoesNotSetVenueWhenEmbeddedNull() {
        event.set_embedded(null);

        EventResponse response = eventMapper.toEventResponse(event);

        assertThat(response.getAddress()).isNull();
        assertThat(response.getCountry()).isNull();
    }

    @Test
    void shouldSetsSelfLink() {
        EventResponse response = eventMapper.toEventResponse(event);

        Map<String, String> selfLink = (Map<String, String>) response.get_links().get("self");
        assertThat(selfLink)
                .containsEntry( "href", "events/evt-001" );
    }
}
