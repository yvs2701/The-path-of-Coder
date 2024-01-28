import { Meteor } from 'meteor/meteor';
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

export const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  let nav = useHistory();

  const submit = e => {
    e.preventDefault();

    Meteor.loginWithPassword({ username: username }, password);
    nav.replace('/');
  };

  return (
    <form onSubmit={submit} className="my-form">
      <div>
        <label htmlFor="username">Username</label>

        <input
          type="email"
          placeholder="user@mailbox.com"
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
        <button type="submit">Log In</button>
      </div>
      <div>
        <a href='/signup'>Sign Up</a>
      </div>
    </form>
  );
};