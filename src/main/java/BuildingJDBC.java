import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.util.StringUtils;

import Constant.SystemConstant;
import Utils.ConnectionUtils;

public class BuildingJDBC {
	

	public static void main(String[] args) {
		Integer floorArea = 100;
		String name = "1";
		String ward = null;
		String street = null;
		String district = null;
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			StringBuilder sql=new StringBuilder("SELECT * FROM building " + SystemConstant.ONE_EQUAL_ONE +"");
			if(floorArea!=null) {
				sql.append(" and floorarea = "+floorArea+"");
			}
			if(!StringUtils.isNullOrEmpty(name)) {
				sql.append(" and name like '%" +name+"%'");
			}
			if(!StringUtils.isNullOrEmpty(ward)) {
				sql.append(" and ward like '%" +ward+"%'");
			}
			if(!StringUtils.isNullOrEmpty(street)) {
				sql.append(" and street like '%" +street+"%'");
			}
			if(!StringUtils.isNullOrEmpty(district)) {
				sql.append(" and district like '%" +district+"%'");
			}
			//Class.forName("com.mysql.jdbc.Driver");
			conn= ConnectionUtils.getConnection();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql.toString());
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
		} catch (SQLException  e) {
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
