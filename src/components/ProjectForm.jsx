import { Grid } from "@material-ui/core"
import './project.css'
import Button from './Button'
import ProjectDropDown from "./ProjectDropDown"
import {useState,useEffect} from 'react'

import axios from 'axios'

import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'

import { CompanyContext } from "../context/CompanyProvider"
import { TeamContext } from "../context/TeamProvider"

import { useHistory } from "react-router-dom"

const ProjectForm = () => {



    const {user,userId,isAdmin,getUser,refreshUserFields} = useContext(UserContext)
    const {companyTeams,getCompanyTeams,getCompanyProjects} = useContext(CompanyContext)
    const {getProjects} = useContext(TeamContext)

    let history = useHistory();
    

    if(companyTeams.length  == 0){
        getCompanyTeams(user.companyId)
        console.log(companyTeams)
    }
    
    const [teamId,updateTeam] = useState()

    

    const teamSubmission = (event) => {
        updateTeam(event.target.value)
    }

    const [name,updateName] = useState()

    const nameSubmission = (event) => {
        updateName(event.target.value)
    }

    const [description,updateDescription] = useState()

    const descriptionSubmission = (event) => {
        updateDescription(event.target.value)
    }

    const sendFormRequest = (event) => {
        event.preventDefault()
        let teamIdSubmit = teamId;
        let descriptionSubmit = description
        let nameSubmit  = name

        if(nameSubmit === undefined) nameSubmit = ''
        if(descriptionSubmit === undefined) descriptionSubmit = ''
        if(isAdmin == false){
            teamIdSubmit = user.teamId
        }
        const request = {
            title: nameSubmit,
            description: descriptionSubmit,
            teamId: teamIdSubmit
        }
        axios.post('/project',request)
        .then(res => {
            getProjects()
            getCompanyProjects(user.companyId)
            if(isAdmin){
                history.push('/adminHomePage')//Redirect to admin home page if admin
            }
            else{
                history.push('/userHome')//Redirect to user home page if user
            }

        }).catch( err => {console.error(err)})
    }
    

    




    const dropDownProps = {isAdmin:isAdmin,teams:companyTeams,submission:teamSubmission,companyId:user.companyId}
    return(
        <section class = "projectContainer">
            <form onSubmit={sendFormRequest}>
                <Grid
                    container
                    direction="column"
                    justify="center"
                    alignItems="center"
                >
                    <div class = "projectContainerBox">
                        <input placeholder = 'Enter project name' type='text' onChange={nameSubmission}/> 
                    </div>
                    <ProjectDropDown>{dropDownProps}</ProjectDropDown>
                    <div class = "projectDescriptionBox">
                        <textarea placeholder = 'Enter project description' onChange={descriptionSubmission}/> 
                    </div>
                    <div className = "buttonMargin"/>
                    <Button type="submit" label="Submit"></Button>
                    
                </Grid>
            </form>
        </section>
    )
}

export default ProjectForm