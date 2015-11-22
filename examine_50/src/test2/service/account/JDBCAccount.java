package test2.service.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test2.exceptions.NullAccountException;
import test2.model.AccountModel;

public class JDBCAccount implements Account{
	private DataSource feeDS;
	private Connection dbConn;
	private PreparedStatement stmt;
	private String SQLCommand;
	
	private int id;
	private String name;
	private int count;
	
	public JDBCAccount() throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("test2/controller/spring.xml");
			InitialContext ctxtJNDI = new InitialContext();
			feeDS = (DataSource)ctxtJNDI.lookup((String)context.getBean("DataSource"));
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void create(AccountModel feeModel) throws Exception {
		SQLCommand = "INSERT INTO Nice (NAME, COUNT) VALUES(?,?)";
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
	public void update(AccountModel feeModel) throws Exception {
		SQLCommand = "UPDATE Nice SET COUNT = ? WHERE NAME = ?";
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
	public void delete(AccountModel feeModel) throws Exception {
		SQLCommand = "DELETE FROM Nice WHERE NAME = ?";
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
	public AccountModel find(AccountModel feeModel) throws Exception {
		SQLCommand = "SELECT * FROM Nice WHERE NAME = ?";
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
	
	public List<AccountModel> list() throws Exception {
		SQLCommand = "SELECT * FROM Nice Order by id";
		List<AccountModel> accountModelList = new ArrayList<AccountModel>();
		
		try {
			dbConn = feeDS.getConnection();
			stmt = dbConn.prepareStatement(SQLCommand);
			ResultSet countResultSet = stmt.executeQuery();
			
			while (countResultSet.next()) {
				AccountModel feeModel = new AccountModel();
				
				id = countResultSet.getInt("ID");
				name = countResultSet.getString("NAME");
				count = countResultSet.getInt("COUNT");
				
				feeModel.setId(id);
				feeModel.setName(name);
				feeModel.setCount(count);
				
				accountModelList.add(feeModel);
			}
			
			stmt.close();
			dbConn.close();
		} catch (Exception e) {
			throw e;
		}
		
		return accountModelList;
	}
}
