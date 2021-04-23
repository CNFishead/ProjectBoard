import React from 'react';
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom';
// import TeamCard from './TeamCard';



export default function ProjectCard({ projects, team }) {


  return projects.map(project => (
    <>
        <Card  className="cardSize mx-3">
          <Link to={{
            pathname: '/viewProject',
            state: {
              projectTitle: project.title,
              teamName: team.teamName,
              projectId: project.id,
              projectDescription: project.description
            }
          }}>
            <Card.Body>
             <b>Project Title</b>
             <hr className='hr'></hr>
              <Card.Text className="title">
                {project.title}<br/>
              </Card.Text>
              <Card.Text className='description'>
              <b>Project Description</b><br/>
              <hr className='hr'></hr>
              </Card.Text>
              <Card.Text className='descriptionText'>
                {project.description}
              </Card.Text>
            </Card.Body>
          </Link>
        </Card>

    </>
  )
  );
}
