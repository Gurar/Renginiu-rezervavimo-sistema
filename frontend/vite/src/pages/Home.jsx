import {useEffect} from 'react';
import { useDispatch, useSelector} from 'react-redux';
import {fetchAllEvents} from '../features/event/eventSlice';
import { EventCard } from '../components';
import { Container, Flex } from '../globalStyle';
const Home = () => {
    const dispatch = useDispatch();
    const events = useSelector(state => state.events.data);
    useEffect(() => {
        dispatch(fetchAllEvents());
    }, [dispatch])
    

    const uniqueEvents = events.filter(
        (event, index, self) =>
            index === self.findIndex(
                (e) => e.title === event.title
            )
    );



    return (
        <>
        <Container>
            <h1>Welcome to my art inspiration</h1> 
            <div className="events-grid">
                {uniqueEvents.map((event) => (
                <EventCard
                    key={event.id}
                    event={event}
                />
                ))}
            </div>
        </Container>
        </>
    )
}

export default Home;