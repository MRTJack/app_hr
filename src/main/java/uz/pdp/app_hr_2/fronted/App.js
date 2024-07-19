import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Login from './components/Login';
import DirectorDashboard from './components/Dashboard/DirectorDashboard';
import HrManagerDashboard from './components/Dashboard/HrManagerDashboard';
import ManagerDashboard from './components/Dashboard/ManagerDashboard';
import EmployeeDashboard from './components/Dashboard/EmployeeDashboard';
import { getToken, getUserRole } from './services/authService';

function PrivateRoute({ component: Component, role, ...rest }) {
    return (
        <Route
            {...rest}
            render={(props) =>
                getToken() && getUserRole() === role ? (
                    <Component {...props} />
                ) : (
                    <Redirect to="/login" />
                )
            }
        />
    );
}

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/login" component={Login} />
                <PrivateRoute path="/director/dashboard" component={DirectorDashboard} role="ROLE_DIRECTOR" />
                <PrivateRoute path="/hr/dashboard" component={HrManagerDashboard} role="ROLE_HR_MANAGER" />
                <PrivateRoute path="/manager/dashboard" component={ManagerDashboard} role="ROLE_MANAGER" />
                <PrivateRoute path="/employee/dashboard" component={EmployeeDashboard} role="ROLE_EMPLOYEE" />
            </Switch>
        </Router>
    );
}

export default App;
