import React, { useState, useContext } from 'react'
import AuthForm from './AuthForm'

import '../styles/AuthForm.css'

import { UserContext } from '../context/UserProvider'

function Auth() {
    const initState = { username: "", password: "", firstName: "", lastName: "", phoneNumber: "", companyId: 1}

    const { signup, login } = useContext(UserContext)

    const [inputs, setInputs] = useState(initState)
    const [toggle, setToggle] = useState(false)

    const handleChange = e => {
        const {name, value} = e.target
        setInputs(prevInputs => ({...prevInputs, [name]: value}))
    }

    const handleSignup = e => {
        e.preventDefault()
        signup(inputs)
        setInputs(initState)
    }

    const handleLogin = e => {
        
        e.preventDefault()
        login(inputs)
        setInputs(initState)
    }

    const toggleForms = () => {
        setToggle(prevToggle => !prevToggle)
    }



    return (
        <div className="form_div">
            
            {!toggle ?
                <>
                    <h2 style={{textAlign: 'center'}}>Login</h2>
                    <AuthForm inputs={inputs}  handleChange={handleChange} handleSubmit={handleLogin} btnText="Login" toggle={toggleForms} toggleText="Not A Member"/>

                </>

            :

                <>
                    <h2 style={{textAlign: 'center'}}>Signup</h2>
                    <AuthForm inputs={inputs} handleChange={handleChange} handleSubmit={handleSignup} btnText="Signup" toggle={toggleForms} toggleText="Already A Member"/>

                </>
                
            
            }

        </div>
    )
}

export default Auth