import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.PreparableStatement;

import Constant.SystemConstant;
import Utils.ConnectionUtils;

public class CustomerJDBC {
	

	public static void main(String[] args) {
		String fullName=null;
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			String sql="SELECT * FROM customer " + SystemConstant.ONE_EQUAL_ONE +"";
			if(!StringUtils.isNullOrEmpty(fullName)) {
				sql +=" and fullname like '%" +fullName+"%'";
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			conn= ConnectionUtils.getConnection();
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
