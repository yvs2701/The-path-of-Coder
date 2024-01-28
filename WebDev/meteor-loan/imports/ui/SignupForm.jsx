import { Meteor } from 'meteor/meteor';
import { Accounts } from 'meteor/accounts-base';
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

export const SignupForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('borrower');
  const nav = useHistory();

  const submit = e => {
    e.preventDefault();

    Accounts.createUser({ username, password, profile: { role } });
    nav.replace('/');
  };

  return (
    <form onSubmit={submit} className="my-form">
      <div>
        <label htmlFor="username">Username</label>

        <input
          type="email"
          placeholder={role+"@mailbox.com"}
          name="username"
          required
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="password">Password</label>

        <input
          type="password"
          placeholder="123456"
          name="password"
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <div>
        <label htmlFor="role">Pick your role:</label>&nbsp;
        <select name="role" value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="admin">Admin</option>
          <option value="borrower">Borrower</option>
          <option value="lender">Lender</option>
        </select>
      </div>

      <div>
        <button type="submit">Sign Up</button>
      </div>
      <div>
        <a href='/'>Log In</a>
      </div>
    </form>
  );
};