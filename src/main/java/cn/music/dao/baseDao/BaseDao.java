package cn.music.dao.baseDao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	private Connection conn;
	private PreparedStatement pstmt;
	protected ResultSet rs;
	
/*	public boolean getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/musicschool","root","08170327");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}*/
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/musicschool", "root", "08170327");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
/*	public Connection getConn(){
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/LittleWhiteDove");
			conn = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}*/
	
	public void closeAll(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int executeUpdate(String sql,Object... params){
		int count = 0;
		this.getConn();
			try {
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
				count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				this.closeAll();
			}

		return count;
	}
	
	public ResultSet executeSql(String sql,Object... params){
		this.getConn();
			try {
				pstmt = conn.prepareStatement(sql);
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return rs;
	}

}
