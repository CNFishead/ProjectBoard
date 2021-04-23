import React, { useContext, useState } from 'react'

import axios from 'axios'

export const CompanyContext = React.createContext()

function CompanyProvider(props) {
    const initState = {
        allCompanies: [],
        companyTeams: [],
        companyProjects: [],
        company: undefined,
        admins: [],
        users: []
    }

    const [companyState, setCompanyState] = useState(initState)

    axios.defaults.baseURL = 'https://api.juliocorzo.com'


    const getCompanies = () => {
        axios.get('/company')
        .then(res => {
            setCompanyState(prevState => ({...prevState, allCompanies: res.data}))
        })
    }

    const getCompany = (companyId) => {
        axios.get('/company/'+companyId)
        .then(res => {
            console.log(res)
            setCompanyState(prevState => ({...prevState, company: res.data}))
        })
    }

    const getCompanyTeams = (companyId) => {
        axios.get(`/company/${companyId}/teams`)
        .then(res => {
            console.log(res.data)
            setCompanyState(prevState => ({...prevState, companyTeams: res.data}))
        })
    }

    const getCompanyProjects = (companyId) => {
        axios.get(`/company/${companyId}/projects`)
        .then(res => {
            setCompanyState(prevState => ({...prevState, companyProjects: res.data}))
        })
    }

    const getAdmins = (companyId) => {
        axios.get(`/company/${companyId}/admins`)
        .then(res => {
            setCompanyState(prevState => ({...prevState, admins: res.data}))
        })
    }

    const getUsers = (companyId) => {
        axios.get(`/company/${companyId}/users`)
        .then (res => {
            setCompanyState(prevState => ({...prevState, users: res.data}))
        })
    }

    return (
        <CompanyContext.Provider value={{
            allCompanies: companyState.allCompanies,
            companyTeams: companyState.companyTeams,
            companyProjects: companyState.companyProjects,
            admins: companyState.admins,
            users: companyState.users,
            getCompanies: getCompanies,
            getCompanyTeams: getCompanyTeams,
            getCompanyProjects: getCompanyProjects,
            company: companyState.company,
            getCompany: getCompany,
            getAdmins: getAdmins,
            getUsers: getUsers
        }} >
        {props.children}
        </CompanyContext.Provider>
    )
}

export default CompanyProvider