import { Meteor } from 'meteor/meteor';
import React, { useEffect, useState } from 'react';
import { useTracker } from 'meteor/react-meteor-data';
import { useFind, useSubscribe } from 'meteor/react-meteor-data';
import { LoansCollection } from '../api/loans';

export const Borrower = () => {
  const user = useTracker(() => Meteor.user({ fields: { _id: 1, username: 1, profile: 1 } }));
  const [amount, setAmount] = useState(0);

  const submit = (e) => {
    e.preventDefault();
    LoansCollection.insert({ amount: parseFloat(amount), user: user._id, paidOff: false, createdAt: new Date()});
  }

  return (
    <>
      <div>
        <h1>{user.profile.role}</h1>
        <p>Welcome {user.username}</p>
        <button onClick={() => Meteor.logout()}>Log Out</button>
      </div>

      <form className='my-form' onSubmit={submit}>
        <div>
          <label htmlFor="username">Amount (in Rs.)</label>

          <input
            type="number"
            placeholder="1000"
            name="amount"
            required
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
        <div>
          <button type="submit">Request Loan</button>
        </div>
      </form>

      <PastLoans />
    </>
  );
}

const PastLoans = () => {
  const loansLoading = useSubscribe('loans');
  const usersLoading = useSubscribe('users');
  const loans = useFind(() => LoansCollection.find());
  const users = useFind(() => Meteor.users.find());

  return (
    <>
      <h2>Unpaid Loans:</h2>
      {loansLoading() && usersLoading() ? <div>Loading...</div> : <div>
        <ul className='my-list'>{
          loans.filter(loan => loan.paidOff === false).map(loan => (
            <li key={loan._id}>
              <div>
                <h5>Requested amount:</h5>
                <p>{loan.amount}</p>
              </div>
              <div>
                <h5>Requested by:</h5>
                <span>{users.filter(user => user._id === loan.user)[0]?.username}</span>
              </div>
            </li>
          ))}</ul>
      </div>}
    </>
  )
}