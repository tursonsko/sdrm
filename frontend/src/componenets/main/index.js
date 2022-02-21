import React, { useState, useEffect } from 'react'
import ImageMapper from 'react-image-mapper';
import image from './../../images/openspace.jpg'
import AREAS_MAP from './map.json'
import {BannerText, BannerSmallerText, Button1, Button2, MainContent, MainBox, FlexBox, FlexText} from './MainStyled'
import { Link } from 'react-router-dom';
import background from './../../images/login-bg.jpg'
import calendaricon from './../../images/calendar-icon.svg'
import restauranticon from './../../images/restaurant-icon.svg'
import officeicon from './../../images/office-icon.svg'

import Calendar from 'react-calendar';
import './../global'
import axios from "axios";

var regions=[]

const MainView = () => {
    const onMapClick = (area, index) => {
        console.log(area.coords)
        const tip = `click map${index + 1}`;
        console.log(tip, area);
        alert(area.coords);
    }
    const onMove=(area,index,event)=>{
        console.log('mose move, x'+event.screenX+ "y:"+ event.screenY)
       
    }

    const onMouseEnter=(area,index,event)=>{
        console.log('On enter coords'+area.coords)
    }
 
    return (
        <div>
            <BannerText>
                S D R M
                <BannerSmallerText>
                    System for booking seats
                </BannerSmallerText>
                <Link to="/login">
                <Button1>
                    Log In
                </Button1>
                </Link>
                <Link to="/registration">
                <Button2>
                    Sign Up Free 
                </Button2>
                </Link>
            </BannerText>
                
            <img background
                        alt="flex"
                        style={{ position:"fixed", top:"0", left:"0", width: "100%", height: "auto", minHeight: "100%", minWidth: "1024px", zIndex: "-1"}}
                        src={background}
                    />
                  
                
                    

                    <div>
                        <MainContent>
                            
                        <h1 style={{ fontSize: '3em', paddingTop: '3%', paddingBottom:'2%'}}>Would you like to reserve a place?</h1>
                        <MainBox>
                            <FlexBox>
                            <img style={{possition:'fixed', width: '60px', height: 'auto', marginLeft:'auto', marginRight:'auto', marginTop:'30px'}}src={calendaricon}/>
                            <h1>Choose your booking time</h1>
                                <FlexText>
                            You decide when you want to book. Choose the time, date, place and seat you want.
                                </FlexText>
                            </FlexBox>

                            <FlexBox>
                            <img style={{possition:'fixed', width: '60px', height: 'auto', marginLeft:'auto', marginRight:'auto', marginTop:'30px'}}src={restauranticon}/>
                            <h1>Restaurants friendly</h1>
                                <FlexText>
                            SDRM also operates restaurants. This will help you reserve your favorite restaurant seat in advance.
                                </FlexText>
                            </FlexBox>
            
                            <FlexBox>
                            <img style={{possition:'fixed', width: '60px', height: 'auto', marginLeft:'auto', marginRight:'auto', marginTop:'30px'}}src={officeicon}/>
                            <h1>Conference rooms</h1>
                                <FlexText>
                            Room service in offices. Conference rooms for one or more people.
                                </FlexText>
                            </FlexBox>
                        </MainBox>
                        

                    
                        </MainContent>
                        
                    </div>
            </div>
        
        
    )
}

export default MainView
