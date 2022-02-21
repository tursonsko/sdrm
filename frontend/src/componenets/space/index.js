import React, { useState, useEffect } from 'react'

import axios from "axios";
import { Row, Col, Card,Button,Divider } from 'antd'

const SpaceView = () => {

    return (
        <div style={{ width: '75', margin: '3rem auto' }}>
        <div style={{ textAlign: 'center' }}>
            <h2>Places to be reserved</h2>
        </div>
    
        <div style={{ margin: '3rem auto' }}>
            {global.products.length === 0 ?
                <div style={{ display: 'flex', height: '300px', justifyContent: 'center', alignItems: 'center' }}>
                    <h2>No post yet...</h2>
                </div> :
                <div>
                    <Row gutter={[16, 16]}>

                        {null}

                    </Row>


                </div>
            }
            <br /><br />
        </div>
    </div>
    );
}

export default SpaceView