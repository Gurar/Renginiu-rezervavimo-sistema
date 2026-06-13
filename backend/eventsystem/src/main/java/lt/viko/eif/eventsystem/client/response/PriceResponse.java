package lt.viko.eif.eventsystem.client.response;

public class PriceResponse {
    private Double min;

    private Double max;

    private String currency;

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public String getCurrency() {
        return currency;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
