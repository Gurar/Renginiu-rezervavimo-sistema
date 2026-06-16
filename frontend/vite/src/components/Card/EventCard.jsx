
import './EventCard.css';
import { Link } from 'react-router-dom';

const EventCard = ({ event }) => {

    const image = event.image;

    const title = event.title;

    const formattedDate =
        event.eventDate ||
        'Date unavailable';

    const address =
        [
            event.address,
            event.country
        ]
        .filter(Boolean)
        .join(', ');

    const price = event.minPrice !== null && event.minPrice !== undefined ? `${event.minPrice} ${event.currency || ''}` : 'Free';

    return (
        <Link to={`/events/${event.id}`}>
            <div className="event-card">

                <img
                    src={image}
                    alt={title}
                    className="event-image"
                />

                <div className="event-content">

                    <h2 className="event-title">
                        {title}
                    </h2>

                    <p className="event-datetime">
                        {formattedDate}
                    </p>

                    <p className="event-address">
                        {address}
                    </p>

                    <p className="event-price">
                        {price}
                    </p>

                </div>
            </div>
        </Link>
    );
};

export default EventCard;

