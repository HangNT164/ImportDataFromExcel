package dao;

import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDao {
    public int add(Connection con, Account account) {
        String query = "INSERT INTO \"TIME_KEEPING\".account (role_id,username,\"password\")\n" +
                "values(?,?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {
            ps.setObject(1, account.getRoleID());
            ps.setObject(2, account.getUsername());
            ps.setObject(3, account.getPassword());
            int isCheck = ps.executeUpdate();
            if (isCheck > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
}
