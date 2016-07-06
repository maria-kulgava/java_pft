package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by Admin on 7/6/2016.
 */
public class DbConnectionTest {

  @Test
  public void testDbCconnection() {
    // устанавливаем соединение с DB
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      // извлекаем данные из DB и помещаем их в специальный список ResultSet
      ResultSet rs = st.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM `group_list`");
      Groups groups = new Groups();
      // пробегаемся по строкам, извлекаем данные и помещаем их в объект GroupData
      while (rs.next()){
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      // нужно закрыть соединение с DB, ресурсы после использования нужно освобождать
      rs.close(); // значит, что мы не собираемся больше читать данные из rs
      st.close(); // знасит, что мы не собираемся больше выполнять никакие запросы
      conn.close(); // закрываем DB

      System.out.println(groups);

      // Do something with the Connection
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
