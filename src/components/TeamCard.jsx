import React from 'react';
import { Card } from 'react-bootstrap'
import UserCard from './UserCard'



export default function TeamCard({ companyTeams }) {

  return (companyTeams.teamName != "" ? companyTeams.map(teams =>  (
    <Card className="cardSize mx-3">
      <Card.Body>
        <Card.Text className="title">
        <b>Team Name</b><br/>
        <hr className='hr'></hr>
        {teams.teamName}<br/><br/>
          <b>Team Description</b>
          <hr className='hr'></hr>
          {teams.text}
        </Card.Text>
        <Card.Text className='description'>
          <b>Team Members</b>
          <hr className='hr'></hr>
        </Card.Text>
        <Card.Text className='descriptionText text-center'>
          <UserCard users={teams.members}></UserCard>
        </Card.Text>
      </Card.Body>
    </Card>
  )
  ) : <div></div>)
}
