/*
 * @Author: Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Date: 2022-08-02 14:26:19 
 * @Last Modified by:   Sakura-Oliver Chen-tutu-布朗尼兔 
 * @Last Modified time: 2022-08-02 14:26:19 
 */
package edu.hit.msc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.hit.msc.db.DBhelper;

public class AdminDao {

	private Connection conn ;

	public AdminDao(){
		try{
			conn= DBhelper.getConnection();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	public boolean updatePassword(String id,String oldPass1,String newPass1,String confirm_password)throws SQLException//�޸�����
	{
		String sql = "select password from admin where id = ?";

		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		ResultSet res =  pst.executeQuery();
		String oldPass2= null;
		while(res.next()){
			oldPass2 = res.getString("password");

		}
		if(oldPass1.equals(oldPass2)&&(newPass1.equals(confirm_password))){
			sql = "update admin set password=? where id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,newPass1);
			pst.setString(2, id);
			int n=pst.executeUpdate();
			if(n==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public boolean updateTeOrStuPassword(String te_no,String stu_no,String password)throws SQLException{

		String sql="update ? set password=? where ";
		PreparedStatement pst = conn.prepareStatement(sql);
		if(stu_no==null)
		{
			pst.setString(1, "teacher");
			pst.setString(2, password);
			pst.setString(3,"te_no="+te_no);
		}else{
			pst.setString(1, "student");
			pst.setString(2, password);
			pst.setString(3,"stu_no="+stu_no);
		}
		int n = pst.executeUpdate();
		if(n==1)
			return true;
		else
			return false;
	}
}







