import React, {useState, useEffect, useContext} from 'react'
import '../styles/AuthForm.css'


import { CompanyContext } from '../context/CompanyProvider'

function AuthForm(props) {
    const {handleChange, handleSubmit, inputs: {email, password, firstName, lastName, phoneNumber, companyId}, btnText, toggle, toggleText } = props

    const { getCompanies, allCompanies } = useContext(CompanyContext)

    let isSignup = false

    if(btnText === "Signup") {
        isSignup = true
    }

    if(allCompanies.length === 0) {
        getCompanies()
    }

    const mappedCompanies = allCompanies.map(company => <option value={company.id}>{company.name}</option>)


    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input type="text" name="email" value={email} onChange={handleChange} placeholder="Email" />
                {/* <label for="username" class="form_label">Username</label> */}
                <input type="password" name="password" value={password} onChange={handleChange} placeholder="Password" />
                {/* <label for="username" class="form_label">Password</label> */}
                {isSignup ? 
                <>
                    <input type="text" name="firstName" value={firstName} onChange={handleChange} placeholder="First Name" />
                    <input type="text" name="lastName" value={lastName} onChange={handleChange} placeholder="Last Name" />
                    <input type="text" name="phoneNumber" value={phoneNumber} onChange={handleChange} placeholder="Phone Number" />
                    <select className="companyDropdown" onChange={handleChange} name="companyId" id="company">
                        {mappedCompanies}
                    </select>
                </>
                :
                <div></div>

                }
                <button type="submit" className="loginbutton">{btnText}</button>
                <button type="button" className="togglebutton" onClick={toggle}>{toggleText}</button>
            </form>
        </div>
    )
}

export default AuthForm