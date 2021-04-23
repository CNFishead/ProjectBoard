import NavBar from './NavBar'
import ViewProfile from './ViewProfile'

const ViewProfilePage = () => {
    return(
        <body>
            <div className = 'page-container'>
                <NavBar></NavBar>
                <ViewProfile>{0}</ViewProfile>
            </div>
        </body>
        
    )
}

export default ViewProfilePage