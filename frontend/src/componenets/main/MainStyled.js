import styled from 'styled-components'
import { NavLink as Link } from 'react-router-dom';
import background from './../../images/background.jpg'
import style from 'react-region-select/lib/style';

//import {NavLink as LinkS} from 'react-scroll'

export const BannerText = styled.div`
font-size:4.5rem;
text-decoration:none;
font-weight: bold;
color: #fff;
margin: auto;
padding: 5%;
text-align: center;
background-image: url(${background});
background-repeat:no-repeat;
background-size: cover;


`
export const BannerSmallerText = styled.div`

font-size:2rem;
text-decoration:none;
font-weight: bold;
color: #fff;
margin: auto;
padding: 1%;
padding-bottom: 3%;
text-align: center;
`
export const Button1 = styled.button`
position:flex;
display:inline-block;
width: 200px;
height: 70px;
border:0.15em solid #FFFFFF;
margin:0 1.0em 0.3em 0;
border-radius:0.3em;
box-sizing: border-box;
text-decoration:none;
font-family:'Roboto',sans-serif;
font-size:1.3rem;
color:#FFFFFF;
text-align:center;
transition: all 0.2s;
background-color:Transparent;
&:hover{
    color:#000000;
    background-color:#FFFFFF;
}
@media all and (max-width:30em){
    a.button1{
    display:block;
    margin:0.4em auto;
`

export const Button2 = styled.button`
position:flex;
display:inline-block;
border:0.15em solid #FFFFFF;
margin:0 0.3em 0.3em 0;
border-radius:0.3em;
box-sizing: border-box;
width: 200px;
height: 70px;
text-decoration:none;
font-family:'Roboto',sans-serif;
font-size:1.3rem;
color:#FFFFFF;
text-align:center;
transition: all 0.2s;
background-color:Transparent;
&:hover{
    color:#000000;
    background-color:#FFFFFF;
}
@media all and (max-width:30em){
    a.button1{
    display:block;
    margin:0.4em auto;

`
export const MainContent = styled.div`
text-align:center;
    width:90%;
    height:auto;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    margin: 0 auto;
    border: 1px solid #dbdbdb;
    border-radius: 0px 0px 5px 5px;
    padding: 20px;
    margin-top: 0vh;
    margin-bottom: 4vh;
    background-color: white;
    box-shadow: 0px 7px 24px 0px rgba(66, 68, 90, 0.6);
`

export const MainBox = styled.div`
display: flex;
flex-wrap: wrap;
justify-content: center;
`
export const FlexBox = styled.div`

  width: 450px;
  margin: 10px;
  text-align: center;
  line-height: 55px;
  font-size: 20px;
`

export const FlexText = styled.div`
width: 300px;
height: 200px;

text-align: center;
justify-content: center;
line-height: 35px;

margin-left: 75px;
`
