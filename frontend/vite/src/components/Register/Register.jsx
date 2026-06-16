import React from 'react';
import {
    Form,
    FormGroup,
    FormInput,
    FormTitle,
    FormText,
    FormLink
} from '../Form/Form.elements';

import { Button } from '../../globalStyle';

const Register = ({ userRegisterInputData, onChange, onClick, link }) => {
    return (
        <>
            <Form>
                <FormTitle>Create Account</FormTitle>
                  <FormGroup>
                    <FormInput
                        type="text"
                        name="username"
                        value={userRegisterInputData.username}
                        placeholder="Prisijungimo Vardas"
                        onChange={onChange}
                    />
                </FormGroup>
                <FormGroup>
                    <FormInput
                        type="text"
                        name="firstName"
                        value={userRegisterInputData.firstName}
                        placeholder="Vardas"
                        onChange={onChange}
                    />
                </FormGroup>
               <FormGroup>
                    <FormInput
                        type="text"
                        name="lastName"
                        value={userRegisterInputData.lastName}
                        placeholder="Pavarde"
                        onChange={onChange}
                    />
                </FormGroup>
                <FormGroup>
                    <FormInput 
                        type="email"
                        name="email"
                        value={userRegisterInputData.email} 
                        placeholder="Email"
                        onChange={onChange}
                    />
                </FormGroup>

                <FormGroup>
                    <FormInput 
                        type="password"
                        name="password"
                        value={userRegisterInputData.password}
                        placeholder="Slaptažodis"
                        onChange={onChange}
                    />
                </FormGroup>
                <Button big type="submit" onClick={onClick} >Log In</Button>
                <FormText>Already have as account ? <FormLink to={link}> Sign in Here</FormLink></FormText>
            </Form>  
        </>
    )
}

export default Register;