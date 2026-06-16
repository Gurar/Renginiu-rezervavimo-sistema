import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchLogin } from '../features/auth/authSlice';
import { LoginForm } from '../components';
import {
  Wrapper,
  Container,
  Flex,
  Button
} from '../globalStyle';
import {REGISTRATION_ROUTE} from '../utils/const'


const Login = () => {
const navigate = useNavigate();
const [userLoginInputData, setUserLoginInputData] = useState({
    'username': '',
    'password': ''
});

const error = useSelector(state => state.auth.error);

const dispatch = useDispatch();


const handleInput = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    setUserLoginInputData({ ...userLoginInputData, [name]: value });
}

const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await dispatch(fetchLogin(userLoginInputData));
    if (response.meta.requestStatus != 'rejected') {
        navigate("/", { replace: true});
    }
}

  return (
    <>
    <Container>
        <Flex  justifyContent="center" alignItems="center">
            <LoginForm
            userLoginInputData={userLoginInputData}
            onChange={handleInput}
            onClick={handleSubmit}
            link={REGISTRATION_ROUTE}
        error={error}
        />
        </Flex>
    </Container>
    </>
  )
}

export default Login