package dao;

import java.util.ArrayList;

import dto.HealthDTO;

public interface DBdao {
	public void add();
	public void mod();
	public HealthDTO selectone();
	public ArrayList<HealthDTO> selectall();
	public void del();
}
