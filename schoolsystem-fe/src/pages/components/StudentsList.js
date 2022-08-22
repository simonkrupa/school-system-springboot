import { Grid, Paper } from '@mui/material';
import React, { Component } from 'react';
 
class StudentList extends Component {
 
    constructor(props) {
        super(props)   
        this.state = {
            records: []
        }
         
    }
 
    componentDidMount() {
        fetch('http://localhost:8080/api/student')
            .then(response => response.json())
            .then(records => {
                this.setState({
                    records: records
                })
            })
            .catch(error => console.log(error))
    }
 
    renderListing() {
        let recordList = []
        const gridStyle = {padding: '50px 20px', margin:"auto", display:"flex"}
        const paperStyle = {padding: '50px 20px', margin:"auto", display:"flex", border: "solid black", borderRadius: "10px", fontSize: "22px", backgroundColor: "#D6D7D6"}


        this.state.records.map(record => {
            return recordList.push(<Grid  style={gridStyle} key={record.id} ><Paper className='pap' elevation={3} style={paperStyle} >
                {record.firstName} {record.lastName} <br></br>
                {record.email}</Paper></Grid>)
        })
 
        return recordList;
    }
 
    render() {
        return (
            <Grid container spacing={2}>
                {this.renderListing()}
            </Grid>
        );
    }
}
 
export default StudentList;