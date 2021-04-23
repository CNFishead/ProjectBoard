import React, { useContext } from 'react'
import { Route, Redirect } from 'react-router-dom'
import { UserContext } from '../context/UserProvider'

function ProtectedRoute(props) {
    const { userId } = useContext(UserContext)
    const { path, redirectTo, component: C} = props
    return (
        userId ?
            <Route to={path} component={C} />
        :
            <Redirect to={redirectTo} />
    )
}

export default ProtectedRoute