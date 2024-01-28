import { Meteor } from 'meteor/meteor';
import { LoansCollection } from '../imports/api/loans.js';
import { Accounts } from 'meteor/accounts-base';

const SEED_USERNAME_SUFFIX = '@mailbox.com';
const SEED_PASSWORD = '123456';
const SEED_ROLES = ['admin', 'lender', 'borrower'];

async function insertLoan({ amount, user, paidOff = false, approvedBy = undefined }) {
  await LoansCollection.insertAsync({ amount, user, paidOff, approvedBy, createdAt: new Date() });
}

Meteor.startup(async () => {
  const SEED_USERS = {};
  console.group('Meteor.startup: USERS DB:');

  SEED_ROLES.forEach((SEED_ROLE) => {
    const SEED_USERNAME = SEED_ROLE + SEED_USERNAME_SUFFIX;
    const fetchedUser = Accounts.findUserByUsername(SEED_USERNAME);
    if (!fetchedUser) {
      const user = Accounts.createUser({
        username: SEED_USERNAME,
        password: SEED_PASSWORD,
        profile: {
          role: SEED_ROLE,
        },
      });
      console.log('Created user, _id:', user);
      SEED_USERS[SEED_ROLE] = user;
    } else {
      SEED_USERS[SEED_ROLE] = fetchedUser._id;
      console.log('User already exists, _id:', fetchedUser._id);
    }
  });
  console.groupEnd();

  console.group('Meteor.startup: LOANS DB:');
  if (await LoansCollection.find().countAsync() === 0) {
    await insertLoan({ amount: 1000, user: SEED_USERS.borrower, paidOff: true, approvedBy: SEED_USERS.lender });
    await insertLoan({ amount: 2000, user: SEED_USERS.borrower });
    await insertLoan({ amount: 3000, user: SEED_USERS.borrower });
  }

  Meteor.publish("loans", function () {
    const loansCollection = LoansCollection.find({}, { sort: { createdAt: -1, updatedAt: 1 }, limit: 50 });
    return loansCollection;
  });

  console.groupEnd();

  Meteor.publish("users", function () {
    const usersCollection = Meteor.users.find({}, { sort: { createdAt: -1, updatedAt: -1 }, limit: 50, fields: { _id: 1, username: 1, profile: 1 } });
    return usersCollection;
  });
});
