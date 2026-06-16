import React from 'react';
import { Flex, Container } from '../../globalStyle';
import { Wrapper, Logo, LogoutButton, LogoutIcon, NavLink} from './Navbar.elements';
import { Avatar, Search} from '..';
import { Navigate } from 'react-router-dom';
import { LOGIN_ROUTE, REGISTRATION_ROUTE, USER_ROUTE, HOME_ROUTE } from '../../utils/const';
import { useDispatch, useSelector } from 'react-redux';
import { fetchLogout } from '../../features/auth/authSlice';

const Navbar = () => {
    const dispacher = useDispatch();
    const isAuth = useSelector(state => state.auth.isAuth);
    const user = useSelector(state => state.auth.user);
    const handleSubmit = async (e) => {
        e.preventDefault();
        dispacher(fetchLogout());
    }
    return (
        <>
            <Wrapper>
                <Container>
                    <Flex alignItems="center">    
                            <Logo to="/">Events</Logo>
                            <NavLink margin="0 25px 0 0" to={HOME_ROUTE}>Home</NavLink>
                            <Search marginRight="auto"/>
                            {isAuth
                            ?
                                <>  
                                    <NavLink margin="0 25px 0 0" to={USER_ROUTE}>My Events</NavLink>
                                    <Avatar size="25px" name={user} src="/favicon.svg"/>
                                    <LogoutButton onClick={handleSubmit}><LogoutIcon/></LogoutButton>
                                    
                                </>
                                
                            : 
                                <>
                                    <NavLink margin="0 25px 0 0" to={LOGIN_ROUTE}>Sign In</NavLink>
                                    <NavLink to={REGISTRATION_ROUTE}>Sign Up</NavLink>
                                    
                                </>
                            }

                            
                            
                    </Flex>
               </Container>
            </Wrapper>    
        </>
    )
}

export default Navbar;