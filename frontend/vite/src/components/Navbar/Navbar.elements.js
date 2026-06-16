import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { Button } from '../../globalStyle';
import { BiLogOut } from 'react-icons/bi';

export const Wrapper = styled.div`
    padding: 15px 16px;
    font-size: 14px;
`;

export const Logo = styled(Link)`
    margin-right: 25px;
`;

export const NavLink = styled(Link)`
    display: inline-block;
    ${({margin}) => margin ? `margin: ${margin};` : ''}
    color: #111;
`;

export const LogoutIcon = styled(BiLogOut)`
    
`; 

export const LogoutButton = styled(Button)`
    margin-left: 30px;
    display: flex;
    font-size: 2.4rem;
`;