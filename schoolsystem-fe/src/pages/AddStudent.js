import React from "react"
import { useState } from "react";
import { Container, TextField, Paper, Button } from "@material-ui/core";



export default function Student() {
    const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
    const[firstName, setFirstName]=useState('')
    const[lastName, setLastName]=useState('')
    const[email, setEmail]=useState('')

    const handleClick=(e)=>{
        e.preventDefault()
        const student={firstName, lastName, email}
        console.log(student)
        fetch("http://localhost:8080/api/student",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(student)
        }).then(()=>{
            console.log("New Student added")
        })
    }
    

    return (

        <Container>
            <Paper elevation={3} style={paperStyle}>
                <h1>Add Student</h1>
        <form noValidate autoComplete="off">
            <TextField id="outlined-basic" label="Student Firstname" variant="outlined" fullWidth
            value={firstName}
            onChange={(e)=>setFirstName(e.target.value)}
            />
            <TextField id="outlined-basic" label="Student Lastname" variant="outlined" fullWidth
            value={lastName}
            onChange={(e)=>setLastName(e.target.value)}
            />
            <TextField id="outlined-basic" label="Student Email" variant="outlined" fullWidth
            value={email}
            onChange={(e)=>setEmail(e.target.value)}
            />
            <Button  onClick={handleClick}>Submit</Button>
        </form>
        </Paper>
        </Container>
    );
}
