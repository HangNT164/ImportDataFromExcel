package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class AccountDetail {
    private int id;
    private String name;
    private String department;
    private String position;
    private Date startTime;
    private Date endTime;
    private String note;
    private int checkEmail;
    private Date workDate;
}
