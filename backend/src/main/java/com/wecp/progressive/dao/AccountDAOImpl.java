package com.wecp.progressive.dao;
import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Accounts;
import com.wecp.progressive.exception.AccountNotFoundException;
import java.util.*;
import java.sql.*;
public class AccountDAOImpl implements AccountDAO{
    private static Connection connection;
    ArrayList<Accounts> arr = new ArrayList<Accounts>();

    static {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int addAccount(Accounts accounts)
    {
        String sql = "INSERT INTO accounts(customer_id, balance) VALUES(?, ?)";
        try(PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            st.setInt(1, accounts.getCustomer_id());
            st.setDouble(2, accounts.getBalance());
            int count = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next())
            {
                accounts.setAccount_id(rs.getInt(1));
            }
            return count;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        /*Accounts acc1 = new Accounts(1001, 1221, 5000.0);
        arr.add(acc1);
        Accounts acc2 = new Accounts(1002, 1222, 6000.0);
        arr.add(acc2);
        
        Accounts acc3 = new Accounts(1003, 1223, 2500.0);
        arr.add(acc3);
        return 0;*/


        
    }
    public Accounts getAccountById(int accountId) throws AccountNotFoundException
    {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";
        try(PreparedStatement st = connection.prepareStatement(sql))
        {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();

            if(rs.next())
            {
                Accounts acc = new Accounts();
                acc.setAccount_id(rs.getInt(1));
                acc.setCustomer_id(rs.getInt(2));
                acc.setBalance(rs.getDouble(3));
                return acc;
                
            }
            else 
            {
                throw new AccountNotFoundException();
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        /*Accounts acc = arr.get(0);
        return acc;*/
    }
    
    public void updateAccount(Accounts accounts) 
    {
        String sql = "UPDATE accounts SET customer_id=? WHERE account_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setInt(1, accounts.getCustomer_id());
            st.setInt(2, accounts.getAccount_id());

            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void deleteAccount(int accountId)
    {
        //arr.remove(0);
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setInt(1, accountId);

            st.executeUpdate();


        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }

        
    }
    public List<Accounts> getAllAccounts() 
    {
        String sql = "SELECT * FROM accounts";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            ResultSet rs = st.executeQuery();
            List<Accounts> arr = new ArrayList<Accounts>();
            while(rs.next())
            {
                int accid = rs.getInt(1);
                int custid = rs.getInt(2);
                double bal = rs.getDouble(3);

                Accounts obj = new Accounts(accid, custid, bal);
                arr.add(obj);
               
            }
            return arr;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        //return arr;
    }




}
