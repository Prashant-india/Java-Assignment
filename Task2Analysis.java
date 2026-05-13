package com.shop;

public class Task2Analysis {
// 1)   T2: Diagnose ConcurrentModificationExceptionANALYSIS Cause:
//    This exception occurs when a collection is modified (added to or removed from) while it
//    is being iterated over using an Iterator (including for-each loops), except through the
// 2)   iterator's own remove() method.Likely Pattern at Line 142: A for-each loop attempting to
//    remove an element:for (Transaction t : transactions)
//    { if (condition) transactions.remove(t);
//    }
//3)Minimal Fix:
//    Use removeIf:transactions.removeIf(t -> t.getAmount() < 0);Or
//    use an Iterator specifically: Iterator<Transaction> it = list.iterator();
//    while(it.hasNext()){
//        if(cond) it.remove();
//    }
}
