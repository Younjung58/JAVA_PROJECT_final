package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public class memberDAO extends DAO{
	
	public static memberDAO memberdao = null;
	
	private memberDAO(){
		
	}
	public static memberDAO getInstance() {
		if(memberdao == null) {
			memberdao = new memberDAO();
		}
		return memberdao; 
	}
	
	
}
