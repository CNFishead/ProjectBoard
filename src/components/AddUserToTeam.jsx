import { Grid } from "@material-ui/core"
import './profile.css'
import Button from './Button'

import { useContext} from 'react'
import { UserContext } from '../context/UserProvider'
import {useState} from 'react'
import { useHistory } from "react-router-dom"
import {CompanyContext} from "../context/CompanyProvider"
import ProjectDropDown from "./ProjectDropDown"
import UserDropDown from './UserDropDown'

import axios from "axios";

const AddUserToTeam = (props) => {
    const {user,isAdmin} = useContext(UserContext)
    const {companyTeams,getCompanyTeams,users,getUsers} = useContext(CompanyContext)

    let history = useHistory();

    if(companyTeams.length  == 0){
        getCompanyTeams(user.companyId)
        console.log(companyTeams)
    }

    if(users.length == 0){
        getUsers(user.companyId)
        console.log(users)
    }

    const teamlessUsers = users.filter(checkUser => checkUser.teamId === null)
    console.log(users)
    console.log(teamlessUsers)
    const [teamId,updateTeam] = useState()

    const teamSubmission = (event) => {
        updateTeam(event.target.value)
        console.log(teamId)
    }

    const [userId,updateUser] = useState()

    const userSubmission = (event) => {
        updateUser(event.target.value)
        console.log(userId)
    }

    const sendFormRequest = (event) => {
        event.preventDefault()
        //Make sure neither the team id or user id is undefined before trying to POST
        if(teamId != undefined && userId != undefined){//Do not use provider as you must do two consective calls
            axios.patch(`team/${teamId}/addUser/${userId}`).then(res => {
                console.log(res)
                //Call getUsers again so that the list of users is refreshed if they try to add more users to teams
                getUsers(user.companyId)
                getCompanyTeams(user.companyId)
                history.push('/adminHomePage')
            })
        }
        
    }

    const dropDownProps = {isAdmin:isAdmin,teams:companyTeams,submission:teamSubmission,companyId:user.companyId}
    const dropDownPropsUser = {isAdmin:isAdmin,users:teamlessUsers,submission:userSubmission}
    return(
        <section className = "projectContainer">
            <div className = "topMargin"/>
            <form className = "formContainer " onSubmit={sendFormRequest}>
                <Grid
                    container
                    direction="column"
                    justify="center"
                    alignItems="center"
                >
                    <h1 className="widthContainer">Add user to team</h1>
                    <ProjectDropDown>{dropDownProps}</ProjectDropDown>
                    <UserDropDown>{dropDownPropsUser}</UserDropDown>
                    <div className = "buttonMargin"/>
                    <Button type="submit" label = "Submit"/>
                </Grid>
            </form>
        </section>
    )
}

export default AddUserToTeam