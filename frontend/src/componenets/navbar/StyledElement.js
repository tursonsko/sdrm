import styled from 'styled-components'
import { NavLink as Link } from 'react-router-dom';
//import {NavLink as LinkS} from 'react-scroll'


export const Nav = styled.nav`
 background: #000;
 height: 80px;
 //margin-top:-80px;
 display: flex;
 justify-content: center;
 font-size: 1rem;
 position:sticky;
 z-index: 10;
 top: 0;
@media screen and (max-width:960px){
  transition: 0.5s all ease;
}
`

export const NavBarContainer = styled.div`
display:flex;
justify-content:space-between;
height:80px;
z-index:1;
width:100%;
padding: 0 24px;
max-width: 1100px;
`

export const NavLogo = styled(Link)`
 color: #fff;
 justify-self:flex-start;
 cursor: pointer;
 font-size:1.5rem;
 display:flex;
 align-items: center;
 margin-left:24px;
 font-weight: bold;
 text-decoration:none;
`
export const NavAdmin = styled(Link)`
 display:flex; 
 color: #fff;
 cursor: pointer;
 font-size:1rem;
 align-items: center;
 padding: 0 1rem;
 margin-right:24px;
 text-decoration:none;
 &.active{
    border-bottom: 3px solid #01bf71;
  }
 @media screen and (max-width: 768px){
    display:none;
}
`

export const MobileIcon = styled.div`
  display:none;
  color: white;
@media screen and (max-width: 768px){
  display:block;
  position: absolute;
  top:0;
  right:0;
  transform: translate(-100%,60%);
  font-size:1.8rem;
  cursor: pointer;
}
`


export const NavMenu = styled.ul`
  display:flex;
  align-items:center;
  list-style:none;
  text-align: center;
  margin-right: 150px;
  @media screen and (max-width: 768px){
      display:none;
  }
`

export const NavItem = styled.li`
 height:80px;
`

export const NavLink = styled(Link)`
  color: #fff;
  display:flex;
  align-items: center;
  text-decoration:none;
  padding: 0 1rem;
  height:100%;
  cursor: pointer;
  &.active{
    border-bottom: 3px solid #01bf71;
  }
`

export const NavBtn = styled.nav`
  display:flex;
  align-items:center;
  @media screen and (max-width: 768px){
      display:none;
  }
`

export const NavBtnLink = styled(Link)`
  border-radius: 50px;
  background: #01bf71;
  padding: 10px 22px;
  white-space:nowrap;
  color: #010606;
  font-size:16px;
  border:none;
  outline:none;
  cursor: pointer;
  transition: all 0.2 ease-in-out;
  text-decoration: none;
 &:hover{
    transition: all 0.2 ease-in-out;
    background: #fff;
    color: #010606;
 }
`