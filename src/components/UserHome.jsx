
import { Row, Container } from 'react-bootstrap'
import AddProject from './AddProject'
import ProjectCard from './ProjectCard'
import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'
import { TeamContext } from '../context/TeamProvider'
import NavBar from './NavBar'
import SolidDivider from './SolidDivider'
import AddTeam from './AddTeam'
import TeamCard from './TeamCard'


const UserHome = (props) => {
    const {user} = useContext(UserContext)

    const {getProjects, projects, getTeams, team, users} = useContext(TeamContext)

    const teamList = []

    if(projects.length === 0) {
        getProjects()
    }
    
    if(team.name === ''){
        getTeams()
        teamList.push(team)

    }
    console.log(user);
    return (
        <>
            <NavBar></NavBar>
            <Container fluid className='hoverAlign'>
                <Row >
                    {user.teamId !== null ? <AddProject></AddProject> : <div></div>}
                    {projects.length !== 0 && user.teamId !== null ? <ProjectCard projects={projects} team={{teamName: team.name}} /> : <p style={{color: "blue"}}>No Projects Found</p> }
                </Row>
            </Container>
        </>
    )

};


export default UserHome;