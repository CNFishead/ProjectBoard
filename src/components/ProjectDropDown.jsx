import './project.css'
import { CompanyContext } from "../context/CompanyProvider"
import { useContext } from 'react'
import React, { useEffect, useState } from 'react'

import { UserContext } from '../context/UserProvider'
/*
{
    isAdmin: boolean that is true if it is a company admin calling the component
    team: the name of the team adminstering the project (only provided if isAdmin is false) and the id {name:string, id:int}
    teams: the array of all teams that could be assigned to this project(only provided if isAdmin is true) [{name:string,id:int}]
    submission: the function that activates when it is submitted
}
*/
const ProjectDropDown = (props) => {

    

    if(props.children.isAdmin){
        return (<select class = "projectContainerBox" onLoad = {props.children.submission} onChange={props.children.submission}><option disabled selected value>-- select an option --</option>{props.children.teams.map(team => <option value={team.id}>{team.teamName}</option>)}</select >)

    }
    else{
        return (<div/>)
    }
    
}

export default ProjectDropDown