package dao;

import entity.AccountDetail;

import java.sql.*;

public class AccountDetailDao {
    public int add(Connection con, AccountDetail student) {
        String query = "INSERT INTO \"TIMEKEEPING\".account_detail (name,department,position,start_time,end_time,note,is_email,work_date)\n" +
                "values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = (con != null) ? con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) : null;) {

            ps.setObject(1, student.getName());
            ps.setObject(2, student.getDepartment());
            ps.setObject(3, student.getPosition());

            if (student.getStartTime() != null) {
                ps.setObject(4, new Time(student.getStartTime().getTime()));
            } else {
                ps.setObject(4, null);
            }
            if (student.getEndTime() != null) {
                ps.setObject(5, new Time(student.getEndTime().getTime()));
            } else {
                ps.setObject(5, null);
            }

            ps.setObject(6, student.getNote());
            ps.setObject(7, student.getCheckEmail());
            ps.setDate(8, new java.sql.Date(student.getWorkDate().getTime()));

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
