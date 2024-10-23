package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MemberDTO;

public class MainFrame extends JFrame implements Frame_, ActionListener {
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
	private JPasswordField pw = new JPasswordField();
	
	private JButton cb1 = new JButton("회원가입");
	private JButton cb2 = new JButton("로그인");
	
	 
	Container con = this.getContentPane();
	
	public MainFrame() {
		this.setBounds(300, 300, 400, 250);
		setResizable(false);
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
		center_p1s.add(pw);	//	 비밀번호 입력 - pw
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
		revalidate();
	    repaint();
		
	    cb1.addActionListener(this);
	    cb2.addActionListener(this);
	    
//		MemberFrame b = new MemberFrame();
		
	}

	// 회원가입 버튼 - cb1 , 로그인 버튼 - cb2
	// 아이디 = ct1 / 비밀번호 - pw

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean flag = true;
		if(e.getSource()==cb1) {
			System.out.println("회원가입 버튼 눌림");
			MemberFrame m = new MemberFrame();
//			m.tempMember(m);
			setVisible(false);
		}
		if(e.getSource()==cb2) {
			System.out.println("로그인 버튼 눌림");
			MemberDTO tempdto = new MemberDTO();
			ArrayList<MemberDTO> mlist = memberdao.selectAll();
			for(MemberDTO m : mlist) {
				if(m.getId().equals(ct1.getText())) {
					tempdto = memberdao.selectOne(ct1.getText());
					String pwt = "";
					char [] pwc = pw.getPassword();
					for (char pwcha : pwc) {
						Character.toString(pwcha);
						// pwcha에 저장된 값을 string의 자료형으로 변환
						pwt+=pwcha;
					}
					if(tempdto.getPw().equals(pwt)) {
						System.out.println("로그인 성공");
						setVisible(false);
						LogFrame log = new LogFrame(tempdto);
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.","어서오세요!",JOptionPane.PLAIN_MESSAGE);
						flag = false;
					}else {
						System.out.println(pwt+"--입력값");
						System.out.println(tempdto.getPw());
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","비밀번호 확인",JOptionPane.WARNING_MESSAGE);
						flag = false;
					}					
					break;
				}
			}
			if(flag) {				
				JOptionPane.showMessageDialog(null, "아이디가 존재하지않습니다.","아이디 확인",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
