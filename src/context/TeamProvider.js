import React, { useState, useContext} from "react";
import { UserContext } from "../context/UserProvider";

import axios from "axios";

export const TeamContext = React.createContext();

function TeamProvider(props) {
  const initState = {
    team: { name: "", text: "" },
    teamId: localStorage.getItem("TeamId") || 0,
    projects: [],
    users: [],
  };

  const { user } = useContext(UserContext);
  const teamId = user.teamId;

  const [teamState, setTeamState] = useState(initState);

  axios.defaults.baseURL = "https://api.juliocorzo.com";

  // console.log(teamState)

  const createTeam = (data) => {
    const newTeam = {
      name: data.name,
      text: data.text,
      companyId: data.companyId,
    };
    axios.post("/team", newTeam);
  };

  const getTeam = () => {
    axios.get(`/team/${teamId}`).then((res) => {
      const { teamName, text, projects, id, members } = res.data;
      setTeamState((prevState) => ({
        ...prevState,
        team: {
          name: teamName,
          text: text,
        },
        teamId: id,
        projects: projects,
        users: members,
      }));
    });
  };


  const getTeamUsers = () => {
      return JSON.parse(axios.get(`/team/${teamState.teamid}/users`))
  }

  const updateTeamProjects = (project,teamId) => {
      let data = {
          name: project.name,
          description: project.description,
          teamId: teamId
      }
      axios.patch(`team/${teamId}/addProject/${project.id}`, data).then((res) => {
        console.log(res)
      })
  }

  const updateTeamMembers = (user) => {
      return JSON.parse(axios.patch(`team/${teamState.teamId}/addUser/${user.userId}`))
  }
  const getProject = () => {
    axios
      .get(`/team/${teamId}/projects`)
      .then((res) => res.data)
      .then((listOfProjects) =>
        setTeamState((prevState) => ({
          ...prevState,
          projects: listOfProjects,
        }))
      );
  };

  return (
    <TeamContext.Provider
      value={{
        team: teamState.team,
        teamId: teamState.teamId,
        projects: teamState.projects,
        users: teamState.users,
        getTeams: getTeam,
        getProjects: getProject,
        createTeam: createTeam,
        getTeamUsers: getTeamUsers,
        updateTeamProjects: updateTeamProjects,
        updateTeamMembers: updateTeamMembers
      }}
    >
      {props.children}
    </TeamContext.Provider>
  );
}

export default TeamProvider;
