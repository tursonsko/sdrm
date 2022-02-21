import React, { useState } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import AdminView from '../componenets/admin/index'
import LoginView from '../componenets/login/index'
import BookingsView from '../componenets/bookings/index'
import MainView from '../componenets/main/index'
import NavigationBar from '../componenets/navbar/NavigationBar'
import SpaceView from '../componenets/space/index'
import UserView from '../componenets/useracc/index'
import RegistrationView from '../componenets/registration/index'
import { Layout } from 'antd';

const HomePage = ({ array }) => {
    const [isOpen, setIsOpen] = useState(false)
    const toggle = () => {
        setIsOpen(!isOpen)
    }

    return (
        <Layout className="layout"style={{minHeight: "100vh", maxheight: "100vh"}}>
        <Router>
            <NavigationBar toggle={toggle} />
            <Switch>
                <Route path='/home' exact component={MainView} />
                <Route path='/space' exact component={SpaceView} />
                <Route path='/bookings' exact component={BookingsView} />
                <Route path='/account' exact component={UserView} />
                <Route path='/login' exact component={LoginView} />
                <Route path='/registration' exact component={RegistrationView} />
                <Route path='/admin' exact component={AdminView} />
            </Switch>
        </Router>
        </Layout>
    )

}

export default HomePage;