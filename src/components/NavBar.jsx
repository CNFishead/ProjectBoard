import NavLinks from './NavLinks'
import './navbar.css'

import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'
import { CompanyContext } from '../context/CompanyProvider'
import { TeamContext } from '../context/TeamProvider'


const NavBar = () => {
    const {user,isAdmin} = useContext(UserContext)
    const {company, getCompany} = useContext(CompanyContext)
    const {team,teamId, getTeams} = useContext(TeamContext)

    if(team == undefined){
        getTeams()
    }
    else if(teamId != user.teamId){
        getTeams()
    }


    if(company == undefined || (user.companyId != company.id)){
        getCompany(user.companyId)
    }
    let companyName = ''
    if(company != undefined){
        companyName = company.name
    }
    
    let header = ''
    if(isAdmin || user.teamId == undefined){
        header = companyName
    }
    else {
        header = companyName + " - " + team.name;
    }
    return(
        <div className="header">
            <p className="companyName">{header}</p>
            <NavLinks></NavLinks>
        </div>
            
    )
}

export default NavBar