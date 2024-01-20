package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Transactions;
import java.sql.*;
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
            st.setString(3, java.sql.Date.valueOf(transaction.getTransaction_date()));
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


}
