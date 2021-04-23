import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from "react-router-dom"
import UserProvider from './context/UserProvider'
import TeamProvider from './context/TeamProvider'
import reportWebVitals from './reportWebVitals';
import CompanyProvider from './context/CompanyProvider';

ReactDOM.render(
  <BrowserRouter>
    <UserProvider>
      <CompanyProvider>
        <TeamProvider>
          <App />
        </TeamProvider>
      </CompanyProvider>
    </UserProvider>
  </BrowserRouter>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
