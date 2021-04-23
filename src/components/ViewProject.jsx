import { Grid } from "@material-ui/core"
import './project.css'
import Button from './Button'
import axios from 'axios'
import { UserContext } from '../context/UserProvider'
import { useHistory } from "react-router-dom"
import { useContext } from 'react'


//TODO: Handle button handler when endpoint is defined
//PROPS
/*
{
    projectTitle: string that contains the name of the project
    teamName: string that contains the name of the team
    projectDescription: string that contains the description of the project
    projectID: id of the project, used for complete button
}
*/
const ViewProject = (props) => {
    console.log(props)
    const {isAdmin} = useContext(UserContext)
    const buttonProps = {label:'Complete'}
    let history = useHistory();

    const complete = () => {
        axios.patch('project/'+props.children.projectId+'/complete').then(res =>{
            console.log('res')
            
            if(isAdmin){
                history.push('/adminHomePage')//Redirect to admin home page if admin
            }
            else{
                history.push('/userHome')//Redirect to user home page if user
            }
        })
    }
    return(
        <section className = "projectContainer">
            <Grid
                container
                direction="column"
                justify="center"
                alignItems="center"
            >
                <div className = "projectContainerBox">
                   <u><p><b>Project Name</b></p></u>
                    <p>{props.children.projectTitle}</p>
                </div>
                <div className = "projectDescriptionBox">
                    <u><p><b>Project Description</b></p></u>
                    <p>{props.children.projectDescription}</p>
                </div>
                <div className = "buttonMargin"/>
                <div>
                    <Button label="Complete" handleClick={complete}>{buttonProps}</Button>
                </div>
                
                
            </Grid>
        </section>
    )
}

export default ViewProject