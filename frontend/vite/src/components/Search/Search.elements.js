import styled from 'styled-components';
import { Button } from '../../globalStyle';
import { AiOutlineSearch } from 'react-icons/ai'

export const Wrapper = styled.div`
    margin-right: auto;
`;

export const Icon = styled(AiOutlineSearch)`
    position: relative;
    top: 1px;
    font-size: 1.6rem;
    text-align: center;
    color: #65676b;
`;

export const Input = styled.input`
    border: none;
    outline: none;
    font-size: 1.4rem;
    letter-spacing: .5px;
    background-color: #f0f2f5;
    padding: 5px;
    border-radius: 33px;
`;

export const SearchButton = styled(Button)`

`;