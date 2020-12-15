package dao;

import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {
    public boolean add(Connection con, Account account) {
        int isCheck = 0;
        String query = "INSERT INTO \"TIMEKEEPING\".account (username,\"password\",role_id)\n" +
                "values(?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {
            ps.setObject(1, account.getUsername());
            ps.setObject(2, account.getPassword());
            ps.setObject(3, account.getRoleID());
            isCheck = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return isCheck > 0;
    }
}
