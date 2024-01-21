package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.dto.CustomerAccountInfo;
import com.wecp.progressive.entity.Customers;
import java.sql.*;
import java.util.*;
import com.wecp.progressive.entity.CustomerNameComparator;
public class CustomerDAOImpl implements CustomerDAO{
    ArrayList<Customers> arr = new ArrayList<Customers>();
    private static Connection connection; 
    static {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*public int addCustomer(Customers customers) 
    {

        String sql = "INSERT INTO customers(name, email, username, password, role) VALUES(?,?,?,?,?)";
        try(PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            st.setString(1, customers.getName());
            st.setString(2, customers.getEmail());
            st.setString(3, customers.getUsername());
            st.setString(4, customers.getPassword());
            st.setString(5, customers.getRole());

            int count = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next())
            {
                customers.setCustomer_id(rs.getInt(1));
            }
            return count;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        

    }*/
    public int addCustomer(Customers customers) 
    {
        Customers c1 = new Customers(1001, "Sumi", "sumki@yahoo.com", "sumidutta24", "dutta24", "Developer");
        Customers c2 = new Customers(1002, "Rani", "ranijoshi@gmail.com", "ranijoshi22", "rani@22", "Tester");
        arr.add(c1);
        arr.add(c2);
        return 0;
    }

    /*public Customers getCustomerById(int customerId) 
    {
        String sql = "SELECT * FROM customers WHERE customer_id=?";
        try(PreparedStatement st = connection.prepareStatement(sql))
        {
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                Customers c = new Customers();
                c.setCustomer_id(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setUsername(rs.getString(4));
                c.setPassword(rs.getString(5));
                c.setRole(rs.getString(6));
                return c;
            }
            

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return null;

    }*/
    public Customers getCustomerById(int customerId) 
    {
        Customers cust = null;
        int flag = 0;
        for(Customers c : arr) 
        {
            if(c.getCustomer_id() == customerId) 
            {
                cust = c;
                flag++;
                break;
            }
        }
        if(flag == 0) 
        {
            return null;
        }
        else 
        {
            return cust;
        }
    }

    /*public List<Customers> getAllCustomers() 
    {
        String sql = "SELECT * FROM customers;";
        List<Customers> arr = new ArrayList<Customers>();
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                Customers c = new Customers();
                c.setCustomer_id(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setEmail(rs.getString(3));
                c.setUsername(rs.getString(4));
                c.setPassword(rs.getString(5));
                c.setRole(rs.getString(6));;
                arr.add(c);
            }
            return arr;
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
            return null;
        }


    }*/

    public List<Customers> getAllCustomers()  
    {
        Collections.sort(arr, new CustomerNameComparator());
        return arr;
    }


    /*public void deleteCustomer(int customerId)
    {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setInt(1, customerId);
            st.executeUpdate();
            

        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }

    }*/

    public void deleteCustomer(int customerId) 
    {
        for(Customers c : arr) 
        {
            if(c.getCustomer_id() == customerId) 
            {
                int index = arr.indexOf(c);
                arr.remove(index);
            }
        }
    }
    public void updateCustomer(Customers customers)  
    {
        for(Customers c : arr) 
        {
            if(c.getCustomer_id() == customers.getCustomer_id()) 
            {
                int index = arr.indexOf(c);
                arr.set(index, customers);
            }
        }
    }
    /*public void updateCustomer(Customers customers) 
    {
        String sql = "UPDATE customers SET name=? WHERE customer_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setString(1, customers.getName());
            st.setInt(2, customers.getCustomer_id());

            st.executeUpdate();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }

    }*/


    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getCustomerAccountInfo'");
        return new CustomerAccountInfo();
    }

}
