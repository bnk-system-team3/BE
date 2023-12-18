package ssg.com.houssg;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class MySQLConnectionTest {
   @Test
   public void TestConnect() throws Exception{
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      
      // 현재 소스 코드는 DB 이름은 test 이고 사용자 및 비밀번호도 test이다.
      // QQQ: 테스트 환경에 적합하게 DB 이름 및 사용자//비밀번호를 수정
      try(Connection con = 
            DriverManager.getConnection(
                  "jdbc:mysql://127.0.0.1:3306/mydb", 
                  "root", 
                  "1234")
            ){
         System.out.println("Connection Success !");
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
}