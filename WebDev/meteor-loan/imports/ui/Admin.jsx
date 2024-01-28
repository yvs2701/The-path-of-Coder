import { Meteor } from 'meteor/meteor';
import React from 'react';
import { useTracker } from 'meteor/react-meteor-data';
import { useFind, useSubscribe } from 'meteor/react-meteor-data';
import { LoansCollection } from '../api/loans';

export const Admin = () => {
  const user = useTracker(() => Meteor.user({ fields: { _id: 1, username: 1, profile: 1 } }));
  return (
    <>
    <div>
      <h1>{user.profile.role}</h1>
      <p>Welcome {user.username}</p>
      <button onClick={() => { Meteor.logout() }}>Log Out</button>
    </div>
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
      <h2>Past Loans:</h2>
      {loansLoading() && usersLoading() ? <div>Loading...</div> : <div>
        <ul className='my-list'>{
          loans.map(loan => (
            <li key={loan._id}>
              <div>
                <h5>Requested amount:</h5>
                <p>{loan.amount}</p>
              </div>
              <div>
                <h5>Requested by:</h5>
                <span>{users.filter(user => user._id === loan.user)[0]?.username}</span>
              </div>
              { loan.paidOff === true ?
                <div>
                  <h5>Approved by:</h5>
                  <span>{users.filter(user => user._id === loan.approvedBy)[0]?.username}</span>
                </div> : null
              }
            </li>
          ))}</ul>
      </div>}
    </>
  )
}