import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { $host, $api } from "../../api/";

const token = localStorage.getItem("token");

const initialState = {
    token: token || '',
    user: null,
    isAuth: !!token,
    status: 'idle',
    error: null
}

export const fetchLogin = createAsyncThunk('auth/fetchLogin', async (userInputData, { rejectWithValue }) => {
    try {
        const response = await $host.post('/auth/signin', { ...userInputData });
        return response.data;
    } catch (error) {
        return rejectWithValue(error.response?.data?.message || "Login failed");
    }
});

export const fetchLogout = createAsyncThunk( "auth/fetchLogout", async () => { 
    localStorage.removeItem("token");
    return true; 
});

export const fetchRegistration = createAsyncThunk('auth/fetchRegistration', async (userInputData, { rejectWithValue, }) => {
    try {
        const response = await $host.post('/auth/signup', { ...userInputData });
    } catch (error) {
        return rejectWithValue(error.response.data.message);
    }
})

// export const fetchAuth = createAsyncThunk('auth/fetchAuth', async (token, {rejectWithValue, dispatch, getState}) => {
//     const state = getState();
//     try {
//         const response = await $api.get('/auth', {
//             headers: {
//                 'Authorization': `Bearer ${state.auth.token.accessToken}`
//             }
//         });
//         return response.data;
//     } catch (error) {
//         return rejectWithValue(error.response.data.message);
//     }

// })

export const fetchAuth = createAsyncThunk( 'auth/fetchAuth', async (_, { rejectWithValue, getState }) => { const state = getState(); try { const response = await $api.get('/auth', { headers: { Authorization: `Bearer ${state.auth.token}` } }); return response.data; } catch (error) { return rejectWithValue( error.response?.data || 'Unauthorized' ); } } );

export const fetchRefresh = createAsyncThunk('auth/FetchRefresh', async(_, {rejectWithValue}) => {
    try {
        const response = await $api.get('/auth/refresh');
        return response.data;
    } catch (error) {
        return rejectWithValue(error.response.data.message);
    }
});


const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducer: {},
    extraReducers(builder) {
        builder
            //Auhorization
            .addCase(fetchAuth.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(fetchAuth.fulfilled, (state, action) => {
                state.status = 'authorized';
                state.error = null
                state.isAuth = true;
                state.user = action.payload.username;
            })
            .addCase(fetchAuth.rejected, (state, action) => {
                localStorage.removeItem('token');
                state.status = 'rejected';
                state.error = action.payload;
                state.isAuth = false;
                state.token = '';
                state.user = null;
            })

            //Registration

            .addCase(fetchRegistration.pending, (state) => {
                state.status = 'loading';
                state.error = null;
            })
            .addCase(fetchRegistration.fulfilled, (state,) => {
                state.status = 'resolved';
                state.error = null;
            })
            .addCase(fetchRegistration.rejected, (state, action) => {
                state.status = 'rejected';
                state.error = action.payload;
            })

            //Login

            .addCase(fetchLogin.pending, (state,) => {
                state.status = 'loading';
                state.error = null;
            })
            .addCase(fetchLogin.fulfilled, (state, action) => {
                state.status = 'resolved';
                state.token = action.payload.token;
                state.user = action.payload.username
                state.isAuth = true;
                state.error = null;
                localStorage.setItem( "token", action.payload.token );
            })
            .addCase(fetchLogin.rejected, (state, action) => {
                state.status = 'rejected';
                state.error = action.payload;
            })

           
            //Logout

            .addCase(fetchLogout.pending, (state,) => {
                state.status = 'loading';
            })
            .addCase(fetchLogout.fulfilled, (state) => {
                state.status = 'resolved';
                state.error = null;
                state.token = '';
                state.isAuth = false;
                state.user = null;
            })
            .addCase(fetchLogout.rejected, (state, action) => {
                state.status = 'rejected';
                state.error = action.payload;
            })
    }

});

export default authSlice.reducer;