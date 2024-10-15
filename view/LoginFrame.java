package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements Frame_ {
										// 고정된 값이 머리글, 바닥글을 구현받음
	
	private JPanel center_p = new JPanel(); // 메인 center
	private JPanel center_p0 = new JPanel();  // 제일 위칸
	private JPanel center_p1s = new JPanel();	// 중간칸 메인
	private JPanel center_p1 = new JPanel();
	private JPanel center_p2 = new JPanel();
	private JPanel center_p01 = new JPanel();
	private JPanel center_p02 = new JPanel();
	private JPanel center_p3 = new JPanel();	// 제일 아래칸
	private JLabel c1 = new JLabel("아이디",JLabel.CENTER);
	private JLabel c2 = new JLabel("비밀번호",JLabel.CENTER);
	private JTextField ct1 = new JTextField();
//	private JTextField ct2 = new JTextField();
	private JPasswordField pw = new JPasswordField();
	
	private JButton cb1 = new JButton("회원가입");
	private JButton cb2 = new JButton("로그인");
	
	
	Container con = this.getContentPane();
	
	
	
	public LoginFrame() {
		this.setBounds(100, 100, 400, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		// 머릿글 제목 추가
		title.setForeground(Color.white);
		title.setFont(font);
		title_p.add(title);
		title_p.setBackground(Color.darkGray);
		
		
		// 바닥글 내용 추가
		bottom_p.setLayout(new GridLayout(1,4));
		bottom.setForeground(Color.white);
		bottom.setFont(font);
		bottom_p.add(bottom);
		bottom_p.setBackground(Color.darkGray);
		this.add(title_p,"North");
		this.add(bottom_p,"South");
		
		// Center 작업
		center_p.setLayout(new GridLayout(3,1));
		// 전체 center_p중 윗쪽칸 작업  -  추후 이미지 추가 예정 **
		center_p.add(center_p0);
		// 전체 center_p중 중간칸 작업
		center_p1s.setLayout(new GridLayout(2,3));
		center_p1.add(c1);		//	 아이디
		center_p1s.add(center_p1);
		center_p1s.add(ct1);	// 아이디 입력 - ct1
		center_p1s.add(center_p01);	//	공백
		center_p2.add(c2);		// 비밀번호 
		center_p1s.add(center_p2);	// 공백
		center_p1s.add(pw);	//	 비밀번호 입력 - ct2
		center_p1s.add(center_p02);
		center_p.add(center_p1s);
		// 버튼 작업 - center_p 아래칸
		cb1.setFont(font);
		cb2.setFont(font);
		cb1.setPreferredSize(new Dimension(100,25));
		cb2.setPreferredSize(new Dimension(80,25));
		center_p3.add(cb1);
		
		center_p3.add(cb2);
		center_p.add(center_p3);
		// 최종 Center구역으로 추가
		this.add(center_p,"Center");
		
	}
}