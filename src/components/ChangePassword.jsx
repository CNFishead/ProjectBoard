import { Grid } from "@material-ui/core"

import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'
import './profile.css'
import Button from './Button'


import {useState} from 'react'

import { useHistory } from "react-router-dom"


const EditProfileFields = () => {

    const {user,patchPassword} = useContext(UserContext)

    let history = useHistory();

    const [oldPassword,updateOldPassword] = useState()

    const oldPasswordSubmission = (event) => {
        updateOldPassword(event.target.value)
    }

    const [newPassword,updateNewPassword] = useState()

    const newPasswordSubmission = (event) => {
        updateNewPassword(event.target.value)
    }

    const [confirmPassword,updateConfirmPassword] = useState()

    const confirmPasswordSubmission = (event) => {
        updateConfirmPassword(event.target.value)
    }

    const sendFormRequest = (event) => {
        event.preventDefault()
        console.log(oldPassword) 
        console.log(newPassword)
        console.log(confirmPassword)
        if(newPassword !== confirmPassword){
            console.log("Passwords do not match")
        }
        const requestBody = {
            email : user.email,
            password : oldPassword,
            newPassword : newPassword
        }
        patchPassword(requestBody)
        history.push('/profile')//Redirect to admin home page
    }

    return(
        <section className = "projectContainer">
            <div classname = "topMargin"/>
            <form classname = "formContainer " onSubmit={sendFormRequest}>
                <Grid
                    container
                    direction="column"
                    justify="center"
                    alignItems="center"
                >
                    <div className="flexContainer">
                        <div className="fieldContainer">
                            <p>Old Password:</p>
                        </div>
                        <div className="fieldContentContainer">
                            <input placeholder = "Old password" type='text' onChange={oldPasswordSubmission}/> 
                        </div>
                        <div className = "emptyCenterer"/>
                    </div>
                    <div className="flexContainer">
                        <div className="fieldContainer">
                            <p>New password:</p>
                        </div>
                        <div className="fieldContentContainer">
                            <input placeholder = "New password" type='text' onChange={newPasswordSubmission}/> 
                        </div>
                        <div className = "emptyCenterer"/>
                    </div>
                    <div className="flexContainer">
                        <div className="fieldContainer">
                            <p>Confirm password:</p>
                        </div>
                        <div className="fieldContentContainer">
                            <input placeholder = "Confirm password" type='text' onChange={confirmPasswordSubmission}/> 
                        </div>
                        <div className = "emptyCenterer"/>
                    </div>
                    <div className = "buttonMargin"/>   
                    <Button type="submit" label = "Submit"/>
                </Grid>
            </form>
        </section>
    )
}

export default EditProfileFields