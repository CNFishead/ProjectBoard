import { Row, Container } from 'react-bootstrap'
import { useContext } from 'react'
import { UserContext } from '../context/UserProvider'
import { TeamContext } from '../context/TeamProvider'
import { CompanyContext } from '../context/CompanyProvider'
import AddProject from './AddProject'
import ProjectCard from './ProjectCard'
import NavBar from './NavBar'
import SolidDivider from './SolidDivider'
import AddTeam from './AddTeam'
import TeamCard from './TeamCard'


const UserHome = (props) => {
    const {user} = useContext(UserContext)
    const {getProjects, projects, getTeams, team, users} = useContext(TeamContext)
    const { companyTeams, getCompanyTeams, getCompanyProjects, companyProjects } = useContext(CompanyContext)

    if(projects.length === 0) {
        getProjects()
    }
    
    if(team.name === '' && user.teamId !== null){
        getTeams()

    }

    if(companyTeams.length === 0) {
        getCompanyTeams(user.companyId)
       
    }

    if(companyProjects.length === 0) {
        getCompanyProjects(user.companyId)
    }
    
    return (
        <section>
            <NavBar></NavBar>
            <Container fluid className='hoverAlign'>
                <Row >
                    <AddTeam></AddTeam>
                    {user.teamId !== null ? <TeamCard companyTeams = {companyTeams} /> : <p style={{color: "blue"}}>No Team Found</p> }
                </Row>
            </Container>
            <SolidDivider></SolidDivider>
            <Container fluid className='hoverAlign'>
                <Row >
                    <AddProject></AddProject>
                    {projects.length !== 0 ? <ProjectCard projects={companyProjects} team={{teamName: team.name}} /> : <p style={{color: "blue"}}>No Projects Found</p> }
                </Row>
            </Container>
            
           
        </section>
    )

};


export default UserHome;