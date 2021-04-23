import React from 'react';
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom';
import { BsFillPlusCircleFill } from 'react-icons/bs'


export default function addProject() {



    return (
        <>
        <div className="relativeCont">
            <Link className='card m-5' to='/addProjectUser'>   {/* need to update link */}
                <Card >
                    <Card.Body className="plusCardSize">
                        <div className='plus '>
                            <span >
                                <BsFillPlusCircleFill size={35} />
                            </span>
                        </div>

                    </Card.Body>
                </Card>
            </Link>

        </div>
        </>
    );
}