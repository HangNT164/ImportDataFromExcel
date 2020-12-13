package dao;

import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {
    public boolean add(Connection con, Account student) {
        int check = 0;
        String query = "INSERT INTO \"TIMEKEEPING\".account (role_id,account_detail_id,username,\"password\")\n" +
                "values(?,?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;){
            ps.setObject(1, student.getRoleID());
            ps.setObject(2, student.getAccountDetailID());
            ps.setObject(3, student.getUsername());
            ps.setObject(4, student.getPassword());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
