package com.wecp.progressive.entity;
import java.util.*;
class AccountBalanceComparator implements Comparator<Accounts>{
    @Override 
    public int compare(Accounts a1, Accounts a2)
    {
        return Double.compare(a1.getBalance(), a2.getBalance());
    }

    
}
