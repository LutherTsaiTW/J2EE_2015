package test1.service.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import test1.model.CarModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test1.exception.NullAccountException;

public class JDBCCar implements Car{
	private DataSource feeDS;
	private Connection dbConn;
	private PreparedStatement stmt;
	private String SQLCommand;
	
	private int id;
	private String name;
	private int count;
	
	public JDBCCar() throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("test1/controller/spring.xml");
			InitialContext ctxtJNDI = new InitialContext();
			feeDS = (DataSource)ctxtJNDI.lookup((String)context.getBean("DataSource"));
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void create(CarModel feeModel) throws Exception {
		SQLCommand = "INSERT INTO Car (NAME, COUNT) VALUES(?,?)";
		try {
			dbConn = feeDS.getConnection();
			
			stmt = dbConn.prepareStatement(SQLCommand);
			stmt.setString(1, feeModel.getName());
			stmt.setInt(2, feeModel.getCount());
			
			stmt.executeUpdate();
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void update(CarModel feeModel) throws Exception {
		SQLCommand = "UPDATE Car SET COUNT = ? WHERE NAME = ?";
		try {
			dbConn = feeDS.getConnection();
			
			stmt = dbConn.prepareStatement(SQLCommand);
			stmt.setInt(1, feeModel.getCount());
			stmt.setString(2, feeModel.getName());
			
			stmt.executeUpdate();
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void delete(CarModel feeModel) throws Exception {
		SQLCommand = "DELETE FROM Car WHERE NAME = ?";
		try {
			dbConn = feeDS.getConnection();
			
			stmt = dbConn.prepareStatement(SQLCommand);
			stmt.setString(1, feeModel.getName());
			
			stmt.executeUpdate();
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public CarModel find(CarModel feeModel) throws Exception {
		SQLCommand = "SELECT * FROM Car WHERE NAME = ?";
		try {
			dbConn = feeDS.getConnection();
			
			stmt = dbConn.prepareStatement(SQLCommand);
			stmt.setString(1, feeModel.getName());
			
			ResultSet countResultSet = stmt.executeQuery();
			
			if (countResultSet.next()) {
				id = countResultSet.getInt("ID");
				count = countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setCount(count);
			} else {
				throw new NullAccountException();
			}
			
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
		
		return feeModel;
	}
	
	public List<CarModel> list() throws Exception {
		SQLCommand = "SELECT * FROM Car Order by id";
		List<CarModel> carModelList = new ArrayList<CarModel>();
		
		try {
			dbConn = feeDS.getConnection();
			stmt = dbConn.prepareStatement(SQLCommand);
			ResultSet countResultSet = stmt.executeQuery();
			
			while (countResultSet.next()) {
				CarModel feeModel = new CarModel();
				
				id = countResultSet.getInt("ID");
				name = countResultSet.getString("NAME");
				count = countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setName(name);
				feeModel.setCount(count);
				
				carModelList.add(feeModel);
			}
			
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
		
		return carModelList;
	}
}
