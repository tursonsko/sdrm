import React, { useState,useEffect } from 'react'
import { Nav, NavLink, NavLogo, NavBarContainer, NavItem, NavMenu, NavAdmin, MobileIcon, NavBtn, NavBtnLink } from './StyledElement'
import { FaBars } from 'react-icons/fa'
import { Switch, Route } from 'react-router-dom'
import { Modal,Form, Input, Button,Layout } from 'antd';
import axios from "axios";
const NavigationBar = ({ toggle }) => {

    const [roles,setRoles] = useState([]);
    const [logged,setLogged] = useState();

    useEffect(() => {
        setRoles((localStorage.getItem('roles')??[]))
        setLogged(localStorage.getItem('islogged'))
    }, []);

    const logout = ()=>{
        setLogged(null);
        setRoles([])
        delete axios.defaults.headers.common['Authorization']
        localStorage.setItem('islogged',null);
     
    }
    return (
        <>
            <Nav>
                <NavBarContainer>
                    <NavLogo to='/home'>SpacePlanner</NavLogo>
                    <MobileIcon onClick={toggle}>
                        <FaBars />
                    </MobileIcon>
                    <NavMenu>
                        <NavItem>
                            <NavLink to="/space">
                                MySpace
                            </NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink to="/bookings">
                                Bookings
                            </NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink to="/account">
                                My account
                            </NavLink>
                        </NavItem>
                    </NavMenu>
                    {roles.includes("ADMIN")?
                    <NavAdmin to="/admin">
                                        Admin
                    </NavAdmin>: <div/>}
                    {logged==null?
                    <NavAdmin to="/login">
                        Login
                    </NavAdmin>
                    :
                    <NavAdmin to="/home" onClick={logout}>
                    Logout
                    </NavAdmin>
                    
                    }
                     {logged==null?
                    <NavAdmin to="/registration">
                        Sign up
                    </NavAdmin>: <div/>}
                </NavBarContainer>
            </Nav>
        </>
    )
}

export default NavigationBar