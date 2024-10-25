package dao;

import java.util.ArrayList;

import dto.memberDTO;
import view.MemberFrame;

public interface DBdao_member {
	public void add(memberDTO memeberdto);
	public ArrayList<memberDTO> selectAll();
	public memberDTO selectOne(String id);
	public void update(String pw, String id);
	public void delete(String id);
}

