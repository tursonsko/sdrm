import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Table, Tag, Space, Layout } from 'antd';
const { Content } = Layout;
const { Column, ColumnGroup } = Table;

const BookingsView = () => {
    const [bookings, setBookings] = useState([]);

    useEffect(() => {
        axios.get(`/api/reservation/user/${localStorage.getItem('userId')}/booked`).then(
            response => {
                console.log(response.data)
                setBookings(response.data)
            },
            err => {
                console.log(err)
            }
        )
    }, []);

    return (

        <Content style={{ padding: '0 50px', minHeight: "100vh", maxheight: "100vh" }} align="center">
            <Table dataSource={bookings} height={"100vh"}>
                <ColumnGroup>
                    <Column title="Reservation Date" dataIndex="timeSlot" key="timeSlot" />
                    <Column title="Date of booking" dataIndex="bookingCreationTime" key="bookingCreationTime" />
                    <Column title="Additional informatrion" dataIndex="personalRequest" key="personalRequest" />

                </ColumnGroup>
                <Column
                    title="Action"
                    key="action"
                    render={(text, record) => (
                        <Space size="middle">
                            <a>Sent me reminder</a>
                            <a>Cancel reservation</a>
                        </Space>
                    )}
                />
            </Table>
        </Content>);
}

export default BookingsView;