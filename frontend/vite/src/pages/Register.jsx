import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchRegistration } from '../features/auth/authSlice';
import { RegisterForm } from '../components';
import {
  Wrapper,
  Container,
  Flex,
  Button
} from '../globalStyle';
import {LOGIN_ROUTE} from '../utils/const'
const Register = () => {
    const navigate = useNavigate();
    const [userRegisterInputData, setUserRegisterInputData] = useState({
        'username': '',
        'password': '',
        'firstName': '',
        'lastName': '',
        'email': ''
    });
    const error = useSelector(state => state.auth.error);
    const dispatch = useDispatch();

    const handleInput = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setUserRegisterInputData({ ...userRegisterInputData, [name]: value });
    }
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await dispatch(fetchRegistration(userRegisterInputData));
        if (response.meta.requestStatus != 'rejected') {
            navigate("/", { replace: true});
        }
    }
    return (
        <>
            <Container>
                <Flex  justifyContent="center" alignItems="center">
                    <RegisterForm
                    userRegisterInputData={userRegisterInputData}
                    onChange={handleInput}
                    onClick={handleSubmit}
                    link={LOGIN_ROUTE}
                error={error}
                />
                </Flex>
            </Container>
        </>
    )
}

export default Register;