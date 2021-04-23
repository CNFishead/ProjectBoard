import { Grid } from "@material-ui/core"
import './profile.css'
import Button from './Button'

import { useContext} from 'react'
import { UserContext } from '../context/UserProvider'
import {useState} from 'react'

import { useHistory } from "react-router-dom"
import {TeamContext} from "../context/TeamProvider"
import { CompanyContext } from "../context/CompanyProvider"


const PostTeamForm = (props) => {
    const {user} = useContext(UserContext)
    const {createTeam} = useContext(TeamContext)
    const {getCompanyTeams} = useContext(CompanyContext)

    
    let history = useHistory();

    const [name,updateName] = useState()

    const nameSubmission = (event) => {
        updateName(event.target.value)
    }

    const [text,updateText] = useState()

    const textSubmission = (event) => {
        updateText(event.target.value)
    }

    const sendFormRequest = (event) => {
        event.preventDefault()
        if(name !==  undefined || user.companyId !== undefined){
            const request = {
                name: name,
                text: text,
                companyId: user.companyId
            }
            createTeam(request)
            getCompanyTeams(user.companyId)//Update the list of company teams
            history.push('/adminHomePage')//Redirect to admin home page
        }
    }

    return(
        <section className = "projectContainer">
            <h1 className="widthContainer">Create new team</h1>
            <form className = "formContainer " onSubmit={sendFormRequest}>
                <Grid
                    container
                    direction="column"
                    justify="center"
                    alignItems="center"
                >
                    <div className="flexContainer">
                        <div className="fieldContainer">
                            <p>Name:</p>
                        </div>
                        <div className="fieldContentContainer">
                            <input placeholder = 'Input team name here' type='text' onChange={nameSubmission}/> 
                        </div>
                        <div className = "emptyCenterer"/>
                    </div>
                    <div class="flexContainer">
                        <div className="fieldContainer">
                            <p>Text:</p>
                        </div>
                        <div className="fieldContentContainer">
                            <input placeholder = 'Input team text here' type='text' onChange={textSubmission}/> 
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

export default PostTeamForm