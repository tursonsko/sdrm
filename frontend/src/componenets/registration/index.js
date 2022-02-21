import React, { useState, useEffect } from 'react'
import { withRouter, useHistory } from 'react-router-dom';
import axios from "axios";
import jwt from 'jwt-decode'
import background from './../../images/login-bg.jpg'
import { Modal,Form, Input, Button,Layout } from 'antd';
import styled from "styled-components";
import {LoginDiv} from './../login/LoginStyled'
import { Link } from 'react-router-dom';

const { Content } = Layout;



const RegistrationView = () => {

    const history = useHistory();
    const [errMessage,setErrMessage] = useState("");
    const [isModalVisible, setIsModalVisible] = useState(false);

    const showModal = () => {
        setIsModalVisible(true);
      };

      const handleOk = () => {
        setIsModalVisible(false);
        history.push("/login")

      };

  

      const handleCancel = () => {
        setIsModalVisible(false);
      };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    const onSubmit = (values)=> {

        let request = {
            name: values.name,
            username: values.username,
            email: values.email,
            password: values.password,
        }
        var instance  = axios.create();
        delete instance.defaults.headers.common['Authorization'];
        instance.post(`/api/signup`, request).then(async (response) => {
           
            if (response.status === 200) {
                showModal();
            }
            return  response.data;
            
        }, []);
    };
    
    return (
        <LoginDiv>
        <Layout>
        <Content style={{ padding: '0% 20%', backgroundColor:'white'}} align="center">

                <h1 style={{ fontSize: '3em', paddingTop: '5%', paddingBottom:'3%'}} align="center">SDRM - Registration</h1>
                
                
            
        <Form
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onSubmit}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
              <Form.Item
                align="center"
                label="Name"
                name="name"
                rules={[{ required: true, message: 'Please input your name!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                align="center"
                label="Username"
                name="username"
                rules={[{ required: true, message: 'Please input your username!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                align="center"
                label="E-mail"
                name="email"
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
                Sign up
                </Button>
                <Modal title="Zaloguj" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel} okText="Zaloguj">
                    <p>Congratulations! You are now signed into SDRM!</p>
                </Modal>
            </Form.Item>
            
        </Form>
        <Link to="/login">
            You have an account? Log in up here
            </Link>
        </Content>
        </Layout>
        </LoginDiv>
    )
}

export default withRouter(RegistrationView)
