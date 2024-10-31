package dao;
import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  
import bean.*;
import db.DBConnection;  
public class EmployeeDao {  
  
	public static int save(Employee e){
		int status=0;
		try{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into employeetable(name,password,email,country) values (?,?,?,?)");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Employee e){
		int status=0;
		try{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("update employeetable set name=?,password=?,email=?,country=? where id=?");
			ps.setString(1,e.getName());
			ps.setString(2,e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4,e.getCountry());
			ps.setInt(5,e.getId());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from employeetable where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Employee getEmployeeById(int id){
		Employee e=new Employee();
		
		try{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from employeetable where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Employee> getAllEmployees(){
		List<Employee> list=new ArrayList<Employee>();
		
		try{
			Connection con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from employeetable");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Employee e=new Employee();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}  