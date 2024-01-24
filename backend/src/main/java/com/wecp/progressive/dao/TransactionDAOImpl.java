package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Transactions;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TransactionDAOImpl implements TransactionDAO{
    private Connection connection;
    public TransactionDAOImpl() {
        try {
            connection =  DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int addTransaction(Transactions transaction) 
    {
        String sql = "INSERT INTO transactions(account_id, amount, transaction_date, transaction_type) VALUES(?, ?, ?, ?)";
        try(PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            st.setInt(1, transaction.getAccount_id());
            st.setDouble(2, transaction.getAmount());
            st.setDate(3,(Date) transaction.getTransaction_date());
            st.setString(4, transaction.getTransaction_type());

            int count = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) 
            {
                transaction.setTransaction_id(rs.getInt(1));
            }
            return count;

        }
        catch(SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }

    }

    public Transactions getTransactionById(int transactionId) 
    {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setInt(1, transactionId);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                Transactions t = new Transactions();
                t.setTransaction_id(rs.getInt(1));
                t.setAccount_id(rs.getInt(2));
                t.setAmount(rs.getDouble(3));
                t.setTransaction_date(rs.getDate(4));
                t.setTransaction_type(rs.getString(5));
                return t;
            }
            else 
            {
                throw new Exception();
            }


        }
        catch(Exception e) 
        {
            e.printStackTrace();
            return null;
        }

    }

    public List<Transactions> getAllTransactions() 
    {
        String sql = "SELECT * FROM transactions";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            ResultSet rs = st.executeQuery();
            List<Transactions> arr = new ArrayList<Transactions>();
            while(rs.next()) 
            {
                Transactions t = new Transactions();
                t.setTransaction_id(rs.getInt(1));
                t.setAccount_id(rs.getInt(2));
                t.setAmount(rs.getDouble(3));
                t.setTransaction_date(rs.getDate(4));
                t.setTransaction_type(rs.getString(5));


            }
            return arr;
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void updateTransaction(Transactions transaction) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void deleteTransaction(int transactionId) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM transactions WHERE tansaction_id = ?";
        try(PreparedStatement st = connection.prepareStatement(sql)) 
        {
            st.setInt(1, transactionId);
            st.executeUpdate();
        }
        catch(SQLException e) 
        {
            e.printStackTrace();
        }
    }

    
}
