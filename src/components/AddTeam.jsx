import React from 'react';
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom';
import { BsFillPlusCircleFill } from 'react-icons/bs'


export default function addTeam() {



    return (
        <>
            <div className="relativeCont">
            <Link className='card m-5' to='/teamPage'>   {/* need to update link */}
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