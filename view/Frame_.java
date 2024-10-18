package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.DBdao_healthCheckup;
import dao.DBdao_healthNormal;
import dao.DBdao_member;
import dao.healthCheckupDAO;
import dao.healthNormalDAO;
import dao.memberDAO;

public interface Frame_ {
				// 프레임별로 고정될 머리글 바닥글을 인터페이스로 구현받도록 설계
	Font font = new Font("Helvetica", Font.BOLD|Font.PLAIN, 15);
	Font font2 = new Font("Helvetica", Font.BOLD, 18);
	Font font3 = new Font("Helvetica", Font.BOLD, 14);
	Font font4 = new Font("Helvetica",Font.ITALIC|Font.BOLD,16);
	
	
	LineBorder warn = new LineBorder(Color.red,2,true);
	LineBorder normal = new LineBorder(Color.black,1,false);
	
	public JPanel title_p = new JPanel();
	public JLabel title = new JLabel(" 나의 건강 지킴이 ");
	public JPanel bottom_p = new JPanel();
	public JLabel bottom = new JLabel(" 개발 : 박연정    ",JLabel.RIGHT);
	
	DBdao_member memberdao = memberDAO.getInstance(); 
	DBdao_healthCheckup healthdao = new healthCheckupDAO();
	DBdao_healthNormal nhealthdao = healthNormalDAO.getInstance();
}
