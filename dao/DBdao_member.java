package dao;

import java.util.ArrayList;

import dto.MemberDTO;
import view.MemberFrame;

public interface DBdao_member {
	public void add(MemberDTO memeberdto);
	public ArrayList<MemberDTO> selectAll();
	public MemberDTO selectOne(String id);
	public void update(String pw, String id);
	public void delete(String id);
}
