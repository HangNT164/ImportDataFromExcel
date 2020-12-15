package dao;

import entity.AccountDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;

public class AccountDetailDao {
    public boolean add(Connection con, AccountDetail accountDetail) {
        int isCheck = 0;
        String query = "INSERT INTO \"TIME_KEEPING\".account_detail (name,department,position,start_time,end_time,note,is_email,work_date,account_id)\n" +
                "values(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query) : null;) {

            ps.setObject(1, accountDetail.getName());
            ps.setObject(2, accountDetail.getDepartment());
            ps.setObject(3, accountDetail.getPosition());

            if (accountDetail.getStartTime() != null) {
                ps.setObject(4, new Time(accountDetail.getStartTime().getTime()));
            } else {
                ps.setObject(4, null);
            }
            if (accountDetail.getEndTime() != null) {
                ps.setObject(5, new Time(accountDetail.getEndTime().getTime()));
            } else {
                ps.setObject(5, null);
            }

            ps.setObject(6, accountDetail.getNote());
            ps.setObject(7, accountDetail.getCheckEmail());
            ps.setDate(8, new java.sql.Date(accountDetail.getWorkDate().getTime()));
            ps.setObject(9, accountDetail.getAccountID());

            isCheck = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return isCheck > 0;
    }
}
