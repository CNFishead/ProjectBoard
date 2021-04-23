import NavBar from './NavBar'
import EditProfileFields from './EditProfileFields'

const EditProfileFieldsPage = () => {
    const EditProfileProps = {userId:0}
    return(
        <body>
            <div className = 'page-container'>
                <NavBar></NavBar>
                <EditProfileFields>{EditProfileProps}</EditProfileFields>
            </div>
        </body>
        
    )
}

export default EditProfileFieldsPage