package com.wecp.progressive.entity;
import java.util.*;
class CustomerNameComparator implements Comparator<Customers>{
    public int compare(Customers c1, Customers c2)
    {
        return c1.getName().compareTo(c2.getName());
    }

    
}
