import React from 'react';
import { Card } from 'react-bootstrap'



const UserCard = ({users}) => {
  return(users !== undefined ? users.map(user => (
    <>
        <p className='descriptionText text-center'>
            {user.profile.firstName}
        </p>
    </> 
  )
  ) : <div></div>
)};


export default UserCard