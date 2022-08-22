import React, { useEffect } from "react"
import { useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import { Container, TextField, Paper, Button } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
        },
    },
}));

export default function Teacher() {
    const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto"}
    const[firstName, setFirstName]=useState('')
    const[lastName, setLastName]=useState('')
    const[email, setEmail]=useState('')
    const[teachers, setTeachers]=useState('')
    const classes = useStyles();
    const handleClick=(e)=>{
        e.preventDefault()
        const teacher={firstName, lastName, email}
        console.log(teacher)
        fetch("http://localhost:8080/api/teacher",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(teacher)
    }).then(()=>{
        console.log("New Teacher added")
    })
}
    

useEffect(()=>{
    fetch("http://localhost:8080/api/student")
    .then(res=>res.json())
    .then((result)=>{
        setTeachers(result);
    }
    )
}, [])


    return (

        <Container>
            <Paper elevation={3} style={paperStyle}>
                <h1 style={{color: "blue"}}>Add Teacher</h1>
        <form className={classes.root} noValidate autoComplete="off">
            <TextField id="outlined-basic" label="Teacher Firstname" variant="outlined" fullWidth
            value={firstName}
            onChange={(e)=>setFirstName(e.target.value)}
            />
            <TextField id="outlined-basic" label="Teacher Lastname" variant="outlined" fullWidth
            value={lastName}
            onChange={(e)=>setLastName(e.target.value)}
            />
            <TextField id="outlined-basic" label="Teacher Email" variant="outlined" fullWidth
            value={email}
            onChange={(e)=>setEmail(e.target.value)}
            />
            <Button style={{color: "blue"}} onClick={handleClick}>Submit</Button>
        </form>
        </Paper>
        </Container>
    );
}

