import NavBar from './NavBar'
import PostTeamForm from './PostTeamForm'
import SolidDivider from './SolidDivider'
import AddUserToTeam from './AddUserToTeam'

const PostTeamFormPage = () => {
    return(
        <body>
            <div className = 'page-container'>
                <NavBar></NavBar>
                <PostTeamForm/>
                <SolidDivider/>
                <AddUserToTeam/>
            </div>
        </body>
    )
}

export default PostTeamFormPage