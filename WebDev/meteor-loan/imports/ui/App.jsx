import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from "react-router-dom";
import { useTracker } from 'meteor/react-meteor-data';
import { LoginForm } from './LoginForm.jsx';
import { SignupForm } from './SignupForm.jsx';
import { Admin } from './Admin.jsx'
import { Borrower } from './Borrower.jsx';
import { Lender } from './Lender.jsx';

export const App = () => {
  const user = useTracker(() => Meteor.user());
  return (
    <Router>
      <Switch>
        <Route exact path="/">{
          user ? (
            user.profile?.role === 'admin' ?
              <Admin /> :
              user.profile?.role === 'borrower' ?
                <Borrower /> :
                user.profile?.role === 'lender' ?
                  <Lender /> : 'ERROR IN USER DATA'

          ) : <LoginForm />
        }</Route>
        <Route exact path="/signup">{
          user ? <Redirect to="/" /> : <SignupForm />
        }</Route>
      </Switch>
    </Router>
  );
}