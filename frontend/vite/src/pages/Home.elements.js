import styled from 'styled-components';
import { HiPlus } from 'react-icons/hi';
import { Link } from 'react-router-dom';
import { IoMdArrowRoundBack }  from 'react-icons/io';
import { Button } from '../globalStyle';

export const Wrapper = styled.div`
    padding: 0 16px;
    position: relative;
`;

export const BuilderWrapper = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    bottom: 72px;
    right: 16px;
    width: 56px;
    height: 56px;
    border-radius: 50%;
    background-color: #fff;
    filter: drop-shadow(rgba(0, 0, 0, 0.2) 0px 0px 8px);
    z-index: 2;
`;

export const BuilderLink = styled(Link)`
    display: block;
    display: flex;
    aling-items: center;
    font-size: 4rem;
    color: #111;
`;

export const Icon = styled(HiPlus)``;