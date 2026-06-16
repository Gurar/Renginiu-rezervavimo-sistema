import React from 'react';
import {
    Form, 
    FormGroup,
    FormInput, 
    FormTitle, 
    FormText,
    FormLink,
} from '../Form/Form.elements';
import { Button  } from '../../globalStyle';

const Login = ({userLoginInputData, onChange, onClick, link, error}) => {
    return (
        <>
            <Form>
                {(error != null) ? error.login : ''}
                <FormTitle>Sign in to Your account</FormTitle>
                <FormGroup>
                    <FormInput 
                        type="username" 
                        name="username"
                        value={userLoginInputData.email} 
                        placeholder="email"
                        onChange={onChange}
                    />
                </FormGroup>

                <FormGroup>
                    <FormInput 
                        type="password" 
                        name="password"
                        value={userLoginInputData.password} 
                        placeholder="password"
                        onChange={onChange}
                    />
                </FormGroup>
                <Button big type="button" onClick={onClick}>Log In</Button>
                <FormText>Don't have an account yet ?<FormLink to={link}> Sign up Here</FormLink></FormText>
            </Form>   
        </>
    )
}

export default Login;