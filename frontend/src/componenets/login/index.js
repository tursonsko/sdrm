import React, { useState, useEffect } from 'react'
import { withRouter, useHistory } from 'react-router-dom';
import axios from "axios";
import jwt from 'jwt-decode'
import { Form, Input, Button,Layout } from 'antd';
import {LoginDiv} from './LoginStyled'
import { Link } from 'react-router-dom';
const { Content } = Layout;

const LoginView = () => {

    const history = useHistory();
    const [errMessage,setErrMessage] = useState("");


    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const onSubmit = (values)=> {

        let request = {
            userName: values.username,
            password: values.password,
        }

        return axios.post('api/login',request).then(async (response) => {
            if (response.status === 200) {
                await localStorage.setItem('token', response.data.token)
                let userData = jwt(response.data.token);
                localStorage.setItem('userId', userData.userId)
                localStorage.setItem('email', userData.email)
                localStorage.setItem('roles', userData.role)
                localStorage.setItem('islogged', true)
                console.log(localStorage.getItem('roles'));
                history.push("/home")
            }
            return response.data;
        }).catch(err => {
            setErrMessage("Wrong user credentaials!")
            console.log(err)
        });
    };
    
    return (
        <LoginDiv>
        <Layout>
        <Content style={{ padding: '0% 20%', backgroundColor:'white'}} align="center">

                <h1 style={{ fontSize: '3em', paddingTop: '5%', paddingBottom:'3%'}} align="center">Log In into SDRM</h1>
                
                
            
        <Form
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onSubmit}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
                align="center"
                label="Username"
                name="username"
                rules={[{ required: true, message: 'Please input your username!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Password"
                name="password"
                rules={[{ required: true, message: 'Please input your password!' }]}
            >
                <Input.Password />
            </Form.Item>

            <p>`{errMessage}`</p>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Log In
                </Button>
            </Form.Item>
            
        </Form>
        <Link to="/registration">
            You don't have an account yet? Sign up here 
            </Link>
        </Content>
        </Layout>
        </LoginDiv>
    )
}

export default withRouter(LoginView)
