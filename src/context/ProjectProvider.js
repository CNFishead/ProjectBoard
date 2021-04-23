import React, {useState, useContext} from 'react'

import axios from 'axios'

export const ProjectContext = React.createContext()

function ProjectProvider(props) {
    const initState = {

    }

    const [projectState, setProjectState] = useState(initState)

    axios.defaults.baseURL = 'https://api.juliocorzo.com'

    const createProject = (data) => {

        axios.post('/project', data)
        .then(res => {
            
        })
    }

    const completeProject = (projectId) => {
        axios.patch('project/'+projectId+'/complete').then(res =>{
            console.log('res')
        })
    }

    return (
        <ProjectContext.Provider value={{
            createProject: createProject,
            completeProject: completeProject
        }} >
            {props.children}
        </ProjectContext.Provider>
    )
}

export default ProjectProvider