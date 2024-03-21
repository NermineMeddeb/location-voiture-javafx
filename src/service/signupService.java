package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SingletonConnection;
import model.Client;
import model.Employee;

public class signupService {
		
	final static String ADMIN = "admin";
	final static String EMPLOYEE = "employee";
	
	public static int signupAdmin(Employee employee) {
		employee.setType(ADMIN);
		return EmployeeService.add(employee);
	}
	
	public static int signupEmployee(Employee employee) {
		employee.setType(EMPLOYEE);
		return EmployeeService.add(employee);
	}
	
	public static int signupClient(Client client) {
		return ClientService.add(client);
		
	}
   
    }
