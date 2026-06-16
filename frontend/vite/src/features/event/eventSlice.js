import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { $host, $api } from "../../api/";


const initialState = {
    data: [],
    status: 'idle',
    reservationStatus: null,
    error: null
}

export const fetchAllEvents= createAsyncThunk('event/fetchAllEvents', async (_, { rejectWithValue }) => {
    try {
        const response = await $host.get('/events');
        return response.data;
    } catch (error) {
        return rejectWithValue(error.response.data.message || 'Failed to fetch events');
    }
});

export const fetchReservation = createAsyncThunk('event/fetchReservation', async (eventId, { rejectWithValue }) => {
    try {
        const response = await $api.post('/reservation', { eventId });
        return response.data;
    } catch (error) {
        return rejectWithValue(error.response?.data?.message || 'Rezervacija nepavyko');
    }
});

const eventSlice= createSlice({
    name: 'events',
    initialState,
    reducer: {},
    extraReducers(builder) {
        builder
            .addCase(fetchAllEvents.pending, (state) => {
                state.status = 'loading'
            })

            .addCase(fetchAllEvents.fulfilled, (state, action) => {
                state.status = 'resolved';
                state.error = null;
                state.data = action.payload || [];
            })

            .addCase(fetchAllEvents.rejected, (state, action) => {
                state.status = 'rejected';
                state.error = action.payload;
            })

            .addCase(fetchReservation.pending, (state) => {
                state.reservationStatus = 'loading';
            })
            .addCase(fetchReservation.fulfilled, (state) => {
                state.reservationStatus = 'success';
            })
            .addCase(fetchReservation.rejected, (state) => {
                state.reservationStatus = 'error';
            })
            
    }
})

export default eventSlice.reducer