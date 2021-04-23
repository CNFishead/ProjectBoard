import './project.css'
import { CompanyContext } from "../context/CompanyProvider"
import { useContext } from 'react'
import React, { useEffect, useState } from 'react'

const UserDropDown = (props) => {   

    if(props.children.isAdmin){
        return (<select class = "projectContainerBox" onLoad = {props.children.submission} onChange={props.children.submission}><option disabled selected value>-- select an option --</option>{props.children.users.map(user => <option value={user.id}>{user.email}</option>)}</select >)

    }
    else{
        return (<div/>)
    }
    
}

export default UserDropDown