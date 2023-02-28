import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.util.StringUtils;

import Constant.SystemConstant;

public class AccountJDBC {
	static final String DB_URL = "jdbc:mysql://localhost:3306/javacore62023";
	static final String USER = "root";
	static final String PASS = "123456";

	public static void main(String[] args) {
		String userName=null;
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			String sql="SELECT * FROM building " + SystemConstant.ONE_EQUAL_ONE +"";
			if(!StringUtils.isNullOrEmpty(userName)) {
				sql +=" and username like '%" +userName+"%'";
			}
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(DB_URL,USER,PASS);
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
			while(rs.next()){
	            //Display values
	            System.out.print("ID: " + rs.getString("id"));
	            System.out.print(", Name: " + rs.getString("name"));
	            System.out.print(", Ward: " + rs.getString("ward"));
	            System.out.print(", Street: " + rs.getString("street"));
	            System.out.print(", District: " + rs.getString("district"));
	            System.out.print(", FloorArea: " + rs.getLong("floorarea"));
	            System.out.println();
	         }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(conn!=null) {
				conn.close();
				}
				if(stm!=null) {
				stm.close();
				}
				if(rs!=null) {
					rs.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
