package dao;

import entity.AccountDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;

public class AccountDetailDao {
    public boolean add(Connection con, AccountDetail accountDetail) {
        int isCheck = 0;
        String query = "INSERT INTO \"TIMEKEEPING\".account_detail (username,name,department,position,work_date,start_time,end_time,note,is_email)\n" +
                "values(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {

            ps.setObject(1, accountDetail.getUsername());
            ps.setObject(2, accountDetail.getName());
            ps.setObject(3, accountDetail.getDepartment());
            ps.setObject(4, accountDetail.getPosition());
            ps.setDate(5, new java.sql.Date(accountDetail.getWorkDate().getTime()));

            if (accountDetail.getStartTime() != null) {
                ps.setObject(6, new Time(accountDetail.getStartTime().getTime()));
            } else {
                ps.setObject(6, null);
            }
            if (accountDetail.getEndTime() != null) {
                ps.setObject(7, new Time(accountDetail.getEndTime().getTime()));
            } else {
                ps.setObject(7, null);
            }

            ps.setObject(8, accountDetail.getNote());
            ps.setObject(9, accountDetail.getCheckEmail());

            isCheck = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return isCheck > 0;
    }
}
