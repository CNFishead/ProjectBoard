import { Grid } from "@material-ui/core"
import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'
import { Link } from 'react-router-dom';

import ProfileFieldComponent from './ProfileFieldComponent'

import './profile.css'
import Button from './Button'


const ViewProfile = (props) => {
    const { user,isAdmin } = useContext(UserContext)
    const firstname = user.firstName
    const lastname = user.lastName
    const phone = user.phoneNumber
    const email = user.email

    const button = () =>{
        console.log('here')
        if(isAdmin){
            return (
                <Link to='/AdminHomePage'><Button label="Admin Homepage" /></Link>
            )
        }
        else{
            return (
                <Link to='/UserHome'><Button label="User Homepage" /></Link>
            )
        }
    }
    
    return (
        <section className="projectContainer">
            <div className="topMargin" />
            <Grid
                container
                direction="column"
                justify="center"
                alignItems="center"
            >
                <ProfileFieldComponent label = 'First name:' content = {firstname}/>
                <ProfileFieldComponent label = 'Last name:' content = {lastname}/>
                <ProfileFieldComponent label = 'Email:' content ={email}/>
                <ProfileFieldComponent label = 'Phone:' content = {phone}/>
                <div className="buttonMargin" />
                <div className="flexContainer">
                    <Link to='/changePassword'><Button label="Change password" /></Link>
                    <div className="buttonMargin" />
                    <Link to='/editProfile'><Button label="Edit fields" /></Link>
                    <div className="buttonMargin" />
                    {button()}
                </div>

            </Grid>
        </section>
    )
}

export default ViewProfile