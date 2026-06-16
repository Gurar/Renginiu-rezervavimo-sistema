import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { fetchAllEvents, fetchReservation } from '../features/event/eventSlice';
import { Container } from '../globalStyle';

const EventDetail = () => {
    const { id } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const events = useSelector(state => state.events.data);
    const isAuth = useSelector(state => state.auth.isAuth); // 👈

    useEffect(() => {
        if (events.length === 0) {
            dispatch(fetchAllEvents());
        }
    }, [dispatch, events.length]);

    const event = events.find(e => String(e.id) === String(id));
    const reservationStatus = useSelector(state => state.events.reservationStatus);
    
    if (!event) return <Container><p>Įvykis nerastas.</p></Container>;

    const address = [event.address, event.country].filter(Boolean).join(', ');
    const price = event.minPrice != null ? `${event.minPrice} ${event.currency || ''}` : 'Nemokama';

    const handleReservation = () => {
        dispatch(fetchReservation(event.id));
    };
    
    return (
        <Container>
            <button onClick={() => navigate(-1)}>← Atgal</button>
            <img src={event.image} alt={event.title} style={{ width: '100%', maxHeight: 400, objectFit: 'cover' }} />
            <h1>{event.title}</h1>
            <p>{event.eventDate || 'Data nenurodyta'}</p>
            <p>{address}</p>
            <p>{price}</p>
            {event.description && <p>{event.description}</p>}

            {isAuth && ( 
                <button onClick={handleReservation}>
                    Rezervuoti
                </button>
                
            )}
            {reservationStatus === 'success' && <p style={{ color: 'green' }}>Rezervacija sėkminga!</p>}
            {reservationStatus === 'error' && <p style={{ color: 'red' }}>Rezervacija nepavyko.</p>}
        </Container>
    );
};

export default EventDetail;