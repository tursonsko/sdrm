import React, { useState, useEffect } from 'react'
import RegionSelect from "react-region-select";
import $ from 'jquery';
import axios from "axios";
import ImagesList from '../SpaceList/index'
import './../global'
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import Dropzone from 'react-dropzone'
import { FcUpload } from 'react-icons/fc'
import { Layout, Button, Input, Typography, Divider } from 'antd';
const { Title } = Typography;
const { Content } = Layout;
const AdminView = () => {

    const [regionsNew, setRegionsNew] = useState([]);
    const regionStyle = {
        background: "rgba(255, 0, 0, 0.5)"
    };
    const onChange = (regions) => setRegionsNew(regions);
    const [value, onChangeDate] = useState(new Date());
    const [ImageValue, setImage] = useState()
    const [spaceName, setSpaceName] = useState();



    const updateImage=(image)=>{
        console.log('new image:'+image)
        setImage(image);
    }

    const addToGlobal = () => {
        if ($('div[data-wrapper=wrapper]').offset() != undefined && $('div[data-dir=sw]').position()) {
            let mainObj = $('div[data-wrapper=wrapper]');
            var top = mainObj[0].getBoundingClientRect().top
            var right = mainObj[0].getBoundingClientRect().right
            var bottom = mainObj[0].getBoundingClientRect().bottom - 130
            var left = mainObj[0].getBoundingClientRect().left
            global.areas.areas.push({ name: "2", shape: "rect", coords: [right, top, left, bottom], preFillColor: "green", fillColor: "blue" })

        }
    }

    const onSpaceNameChange = (event) => {
        setSpaceName(event.target.value)
    }

    const onDropImage = async (files) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(files[0])
        fileReader.onload = setTimeout(function () {
            setImage(fileReader.result);

        }, 1000);
        fileReader.onerror = (error) => {
            console.log(error);
        }
    };

    const sendImgToServer = () => {
        axios.post('api/manage/space', { name: spaceName, imageBase64: ImageValue, userId: `${localStorage.getItem('userId')}` }).then(async (response) => {
            if (response.status === 200) {
                await setImage(response.data.imageBase64)
            }
            return response.data;
        }).catch(err => {
            console.log('poszedl req z ' + ImageValue)
            console.log(err)
        });
    }


    const changeRegionData = (index, value) => {
        setRegionsNew((prevRegions) => {

            const region = prevRegions[index];
            let color;
            switch (value) {
                case "1":
                    color = "rgba(0, 255, 0, 0.5)";
                    break;
                case "2":
                    color = "rgba(0, 0, 255, 0.5)";
                    break;
                case "3":
                    color = "rgba(255, 0, 0, 0.5)";
                    break;
                default:
                    color = "rgba(0, 0, 0, 0.5)";
            }
            console.log("nowy region");
            addToGlobal();
            return [

                ...prevRegions.slice(0, index),
                {
                    ...region,
                    data: {
                        ...region.data,
                        dataType: value,
                        regionStyle: { ...region.data.regionStyle, background: color, id: 1 }
                    }
                },
                ...prevRegions.slice(index + 1)
            ];
        });
    };
    const regionRenderer = (regionProps) => {
        if (!regionProps.isChanging) {

            return (
                <div style={{ position: "absolute", right: 0, bottom: "-1.5em" }}>
                    <select
                        onChange={(event) =>
                            changeRegionData(regionProps.index, event.currentTarget.value)
                        }
                        value={regionProps.data.dataType}
                    >
                        <option value="1">Green</option>
                        <option value="2">Blue</option>
                        <option value="3">Red</option>
                    </select>
                </div>
            );
        }
    };

    return (
        <div style={{ display: "flex" }} align="center">
            <div style={{ flexGrow: 1, flexShrink: 1, width: "50%" }}>
               <ImagesList handleChangeOnImage={image=>updateImage(image)}/>
                {ImageValue === undefined ||ImageValue==null ?
                    <p /> : <RegionSelect className="rectangle_custom" id="test"
                        regions={regionsNew}
                        regionStyle={regionStyle}
                        constraint
                        onChange={onChange}
                        regionRenderer={regionRenderer}
                    >{ImageValue === undefined ||ImageValue==null ? (<Layout style={{ padding: '50px' }} align="center">
                        <Content>Please load image </Content> </Layout>)
                        : <img
                            alt="space"
                            style={{ width: "1100px", height: "850px" }}
                            src={ImageValue}
                        />}

                    </RegionSelect>}
            </div>
            <div style={{ flexGrow: 1, flexShrink: 1, width: "0%", padding: 0 }}>
                <Calendar
                    onChange={onChangeDate}
                    value={value}
                />
                <Divider />
                <Title level={3}>Please load data by click below</Title>
                <div>
                    <div style={{ display: 'felx', justifyContent: 'space-between' }}>

                        <Dropzone onDrop={onDropImage}
                            multiple={false}
                            maxSize={9999999999}
                            accept="image/*"
                        >
                            {({ getRootProps, getInputProps }) => (
                                <div style={{
                                    border: '1px solid lightgray', display: 'table'
                                }}
                                    {...getRootProps()}
                                >
                                    <input {...getInputProps()} />
                                    <FcUpload style={{ display: 'table-cell', verticalAlign: 'middle', textAlign: 'center' }} size={'120px'} />
                                </div>

                            )}
                        </Dropzone>
                        <Divider />
                        <Title level={3}>Send data to server</Title>
                        {
                            ImageValue === undefined ? "No selected data to send" : (<div>
                                <p>Please provide space name</p>
                                <div>
                                    <Input style={{ width: '50%' }} onChange={onSpaceNameChange} value={spaceName} /></div>
                                <Button onClick={sendImgToServer}>Send image to server.</Button></div>)
                        }

                    </div>
                </div>
            </div>
        </div>);
};








export default AdminView