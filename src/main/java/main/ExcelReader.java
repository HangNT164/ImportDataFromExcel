package main;

import dao.AccountDao;
import dao.AccountDetailDao;
import entity.Account;
import entity.AccountDetail;
import jdbc.PostgresConnection;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

public class ExcelReader {
    public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\HangNT\\Documents\\GitHub\\TimeKeeping\\a.xlsx";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        AccountDetail accountDetail = null;
        Account account = null;

        try {
            FileInputStream inputStream = new FileInputStream(SAMPLE_XLSX_FILE_PATH);

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            connection = PostgresConnection.getConnection();
            connection.setAutoCommit(false);

            rowIterator.next();
            rowIterator.next();
            rowIterator.next();

            account = Account.builder().build();
            accountDetail = AccountDetail.builder().build();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                // tao xong 1 object
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {

                        case 0: {
                            String username = nextCell.getStringCellValue();
                            account.setUsername(username);
                            break;
                        }

                        case 1: {
                            String name = nextCell.getStringCellValue();
                            accountDetail.setName(name);
                            break;
                        }

                        case 2: {
                            String department = nextCell.getStringCellValue();
                            accountDetail.setDepartment(department);
                            break;
                        }

                        case 3: {
                            String position = nextCell.getStringCellValue();
                            accountDetail.setPosition(position);
                            break;
                        }

                        case 4: {
                            Date date = nextCell.getDateCellValue();
                            accountDetail.setWorkDate(date);
                            break;
                        }

                        case 6: {
                            Date startTime = nextCell.getDateCellValue();
                            accountDetail.setStartTime(startTime);
                            break;
                        }

                        case 7: {
                            Date endTime = nextCell.getDateCellValue();
                            accountDetail.setEndTime(endTime);
                            break;
                        }

                        case 9: {
                            String note = nextCell.getStringCellValue();
                            accountDetail.setNote(note);
                            break;
                        }

                        case 10: {
                            int emailNumber;
                            String email = nextCell.getStringCellValue();
                            if (email == "") {
                                emailNumber = 0;
                            } else if (email.equalsIgnoreCase("Chưa mail")) {
                                emailNumber = 1;
                            } else {
                                emailNumber = 2;
                            }
                            accountDetail.setCheckEmail(emailNumber);
                            break;
                        }
                    }
                }

                // sau khi tao 1 object => add account detail + add account

                // add accountDetail
                int accountDetailID = new AccountDetailDao().add(connection, accountDetail);

                // add account
                account.setAccountDetailID(accountDetailID);
                account.setRoleID(1);
                account.setPassword("123456");
                boolean addAccount = new AccountDao().add(connection, account);
            }

            // dong excel
            workbook.close();


        } catch (Exception exception) {
            connection.rollback();
            exception.printStackTrace(System.out);
        } finally {
            // commit data after add
            connection.commit();
            connection.close();
        }
    }
}