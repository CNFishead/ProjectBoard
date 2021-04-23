import React, {useContext} from 'react'
import Auth from './components/Auth'
import ViewProjectPage from './components/ViewProjectPage'
import AddProjectUser from './components/AddProjectUser'
import ViewProfilePage from './components/ViewProfilePage'
import UserHome from './components/UserHome'
import ChangePasswordPage from './components/ChangePasswordPage'
import AdminHomePage from './components/AdminHomePage'
import ProtectedRoute from './shared/ProtectedRoute';
import EditProfileFieldsPage from './components/EditProfileFieldsPage'
import PostTeamFormPage from './components/PostTeamFormPage'

import './App.css';
import './index.css'
import './styles/Button.css';
import './styles/ProjectCard.css'
import './styles/addCard.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import { Switch, Route, Redirect } from 'react-router-dom'
import { UserContext } from './context/UserProvider'
import TeamCard from './components/TeamCard'


function App() {

  const { userId, logout, user, isAdmin } = useContext(UserContext)
  
  
  return (
    <>
        
      <Switch>
        {/* <Route exact path="/" render={rProps => userId ? <Redirect to='/adminHomePage'/> : <Auth {...rProps} />} /> */}
        <Route exact path="/" render={rProps => userId ? (isAdmin ?  <Redirect to='/adminHomePage'/> : <Redirect to='/userHome'/>) : <Auth {...rProps} />} />

        {/* <ProtectedRoute path="/Home" components={ViewProject} redirectTo="/" /> */}
{/* 
        <ProtectedRoute path="/viewProject" component={ViewProjectPage} redirectTo="/" />

        <ProtectedRoute path="/addProjectUser" component={AddProjectUser} redirectTo="/"/>

        <ProtectedRoute path="/addProjectAdmin" component={AddProjectAdmin} redirectTo="/"/>
*/}
        <ProtectedRoute path="/profile" component={ViewProfilePage} redirectTo="/"/>

        <ProtectedRoute path = "/editProfile" component={EditProfileFieldsPage} redirectTo="/"/>
        
        <ProtectedRoute path="/viewProject" component={ViewProjectPage} redirectTo="/"/>

        <ProtectedRoute path="/addProjectUser" component={AddProjectUser} redirectTo="/" />

        <ProtectedRoute path="/changePassword" component={ChangePasswordPage} redirectTo="/" />


        {/* <Route path="/profile" component={ViewProfilePage} /> */}

        <ProtectedRoute path='/userHome' component={UserHome} redirectTo="/"/>
        <ProtectedRoute path='/adminHomePage' component={AdminHomePage} />
        <ProtectedRoute path='/teamPage' component={PostTeamFormPage} />
 
        {/* 404 error handling */}
        <Route render={
          () => <h1>404 Not Found</h1>
        } />

      </Switch>





    </>
  );
}

export default App;
