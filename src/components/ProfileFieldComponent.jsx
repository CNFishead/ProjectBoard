import './profile.css'

const ProfileFieldComponent = ({ label, content }) => {


    if(content != undefined){
        return(
        <div class="flexContainer">
            <div className="fieldContainer">
                <p>{label}</p>
            </div>
            <div className="fieldContentContainer">
                <p>{content}</p>
            </div>
            <div className="emptyCenterer" />
        </div>)
    }
    else{
        return(<div/>)
    }
}

export default ProfileFieldComponent