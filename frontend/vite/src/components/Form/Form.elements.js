import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const Form = styled.form`
    padding: 46px;
    width: 450px;
    background: #fff;
    box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
`;

export const FormGroup = styled.div`
    position: relative;
    width: 100%;
    margin-bottom: 20px;
    :last-of-type {
        margin-bottom: 0;
    }
`;


export const FormLabel = styled.label`
    display: inline-block;

`;

export const FormInput = styled.input`
    width: 100%;    
    display: inline-block;
    height: 42px;
    outline: none;
    text-indent: 10px;
    background-color: #F2F2F2;
    border-style: solid;
    border-color: #D8D6D8;
    border-width: 1px;
    font-size: 1.4rem;
`;

export const FormCheck = styled.input`

`;

export const FormTitle = styled.h3`
    font-size: 1.8rem;
    text-align: center;  
    margin-bottom: 48px;
`;

export const FormText = styled.h4`
    font-size: 1.4rem;
    font-weight: 400;
    margin-top: 30px;
    text-align: center;
`;

export const FormLink = styled(Link)`
    font-weight: 700;
    color: inherit;
`;