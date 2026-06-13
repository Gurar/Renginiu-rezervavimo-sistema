package lt.viko.eif.eventsystem.client.response;

public class VenueResponse {
    private AddressResponse address;

    private CountryResponse country;

    public AddressResponse getAddress() {
        return address;
    }

    public CountryResponse getCountry() {
        return country;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    public void setCountry(CountryResponse country) {
        this.country = country;
    }
}
