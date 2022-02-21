import React, { useState, useEffect } from 'react'
import { Layout, Button, Input, Typography, Divider } from 'antd';
import { Select } from './StyledComponent';
import axios from "axios";
const { Title } = Typography;
const { Content } = Layout;


const ImagesList = (props) => {

    const [listOfSpaces, setListOfSpaces] = useState([])

    const [spaceNameSelected, setSpaceNameSelect] = useState(0)
    const [chosenImage, setNewChosenImage] = useState(0)


    const onChangeSpaceImageName = (e) => {
        setSpaceNameSelect(e.target.value);
    }

    const handleChangeOnImage = (value) => {
        props.handleChangeOnImage(value);
    }


    useEffect(() => {
        axios.get(`/api/manage/space/`).then(
            response => {
                console.log(response.data)
                setListOfSpaces(response.data)
            },
            err => {
                console.log(err)
            }
        )
    }, []);

    return (
        <div>
            <Layout>
                <Content style={{ padding: "1%" }}>
                    {(!listOfSpaces.length) ? <Title level={3}>Nothing to present, please upload data.</Title> :
                        <div><Select onChange={onChangeSpaceImageName} style={{ width: '50%' }}>
                            {
                                listOfSpaces.map(item => (
                                    <option key={item.idSpace} value={item.imageBase64}>{item.name}</option>

                                ))}
                        </Select>

                            <Button onClick={() => handleChangeOnImage(spaceNameSelected)}>Load image</Button>
                        </div>}
                </Content>
            </Layout>
        </div>
    );
}

export default ImagesList