package service.fee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.FeeModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exceptions.NullAccountException;

public class JDBCFee implements Fee{
	private DataSource feeDS;
	private Connection dbConn;
	private PreparedStatement stmt;
	private String SQLCommand;
	
	private int id;
	private String name;
	private int count;
	
	public JDBCFee() throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring/controller/database/spring.xml");
			InitialContext ctxtJNDI = new InitialContext();
			feeDS = (DataSource)ctxtJNDI.lookup((String)context.getBean("DataSource"));
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void create(FeeModel feeModel) throws Exception {
		SQLCommand = "INSERT INTO Fee (NAME, COUNT) VALUES(?,?)";
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
	public void update(FeeModel feeModel) throws Exception {
		SQLCommand = "UPDATE Fee SET COUNT = ? WHERE NAME = ?";
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
	public void delete(FeeModel feeModel) throws Exception {
		SQLCommand = "DELETE FROM Fee WHERE NAME = ?";
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
	public FeeModel find(FeeModel feeModel) throws Exception {
		SQLCommand = "SELECT * FROM Fee WHERE NAME = ?";
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
	
	public List<FeeModel> list() throws Exception {
		SQLCommand = "SELECT * FROM Fee Order by id";
		List<FeeModel> feeModelList = new ArrayList<FeeModel>();
		
		try {
			dbConn = feeDS.getConnection();
			stmt = dbConn.prepareStatement(SQLCommand);
			ResultSet countResultSet = stmt.executeQuery();
			
			while (countResultSet.next()) {
				FeeModel feeModel = new FeeModel();
				
				id = countResultSet.getInt("ID");
				name = countResultSet.getString("NAME");
				count = countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setName(name);
				feeModel.setCount(count);
				
				feeModelList.add(feeModel);
			}
			
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
		
		return feeModelList;
	}
}
