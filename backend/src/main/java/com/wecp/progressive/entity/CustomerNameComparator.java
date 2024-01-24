package com.wecp.progressive.entity;
import java.util.*;
public class CustomerNameComparator implements Comparator<Customers>{
    @Override
    public int compare(Customers c1, Customers c2)
    {
        return c1.getName().compareTo(c2.getName());
    }

    
}
