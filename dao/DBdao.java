package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public interface DBdao {
	public void add();
	public void mod();
	public ArrayList<HealthDTO> select();
	public void del();
}
