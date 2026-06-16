import {useEffect} from 'react';
import { Routes, Route } from 'react-router-dom';
import { useDispatch} from 'react-redux';
import {
  Layout,
} from './components';
import {
  Home,
  Login,
  User,
  Register,
  Event
} from './pages';
import {PublicRoute, ProtectedRoute} from './routes'
import { HOME_ROUTE, LOGIN_ROUTE, REGISTRATION_ROUTE, USER_ROUTE } from './utils/const';
import { fetchAuth } from './features/auth/authSlice';

function App() {
const dispatch = useDispatch();
const token = localStorage.getItem('token');
useEffect(() => {

  if (token) {
    dispatch(fetchAuth());
  }

}, [dispatch, token]);
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/*Public Routes*/}
        <Route path="" element={
          <PublicRoute>
            <Home />
          </PublicRoute>
        }/>
          <Route path="events/:id" element={
            <PublicRoute>
              <Event/>
            </PublicRoute>
          } />
        <Route path={LOGIN_ROUTE} element={
          <PublicRoute>
            <Login />
          </PublicRoute>
        } />
        <Route path={REGISTRATION_ROUTE} element={
          <PublicRoute>
            <Register/>
          </PublicRoute>
        } />
        {/*Protected Routes*/}
        <Route path={USER_ROUTE} element={
          <ProtectedRoute>
            <User />
          </ProtectedRoute>
        }/> 
        <Route path="*" element={
          <main style={{ padding: "1rem" }}>
            <p>There's nothing here!</p>
          </main>
        } />
      </Route>
    </Routes>
  )
}

export default App
