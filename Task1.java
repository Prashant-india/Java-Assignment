package com.shop;

public class Task1 {
    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {
        //FIX: Initialize to empty list to prevent NPE
        List<LoanAccount> result = new ArrayList<>();
        if(accounts == null) return result;

        for (LoanAccount account : accounts) {
            //FIX : Added null check getDueDate() to prevent NPE
            if (account.getDueDate()!=null && account.getDueDate().before(new Date())) {
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }
        return result;
    }

// LoanAccount fields:
// Date dueDate          — may be null for restructured accounts
// double outstandingBalance
// String accountId      — always non-null

}
