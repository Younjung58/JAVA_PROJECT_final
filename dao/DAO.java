package dao;

import java.util.ArrayList;

import dto.HealthDTO;
import dto.MemberDTO;

public class DAO implements DBdao{
	
	public void init() {
		try {
			Class.forName(driverName);
			System.out.println("오라클 드라이버 로드 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	 public void add(MemberDTO memberdto) {
		 
	 }
	 public void mod(int num) {
		 
	 }
	 public ArrayList<MemberDTO> mselectall() {
		 return null;
	 }
	 public ArrayList<HealthDTO> hselectall() {
		 return null;
	 }
	 public MemberDTO mselectone() {
		 return null;
	 }
	 public HealthDTO hselectone() {
		 return null;
	 }
	 public void delete(int num) {
		 
	 }

}
