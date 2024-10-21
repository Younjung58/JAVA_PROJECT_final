package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.MemberDTO;

public class LogFrame extends JFrame implements Frame_,ActionListener{
	
	private JLabel titlesub1,titlesub2,titlesub2s;
	private JButton sel1, sel2, sel3, sel4, sel5;
	
	Container con = this.getContentPane(); 
	MemberDTO memberdto = null;

	public LogFrame(MemberDTO memberdto) {
		this.memberdto = memberdto;
		
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
		
		// 중간 내용 추가
		JPanel p = new JPanel();
		titlesub1 = new JLabel(memberdto.getId() + " 님 어서오세요 !!");
		titlesub1.setFont(font2);
		p.add(titlesub1);
		JPanel p1 = new JPanel(new GridLayout(1,2,5,0));
		ImageIcon icon = new ImageIcon("images/3017604.png");
		Image img = icon.getImage();
		Image chimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		icon = new ImageIcon(chimg);
		titlesub2 = new JLabel(icon, JLabel.CENTER);
		p1.add(titlesub2);
		JPanel p2 = new JPanel(new GridLayout(7,1,5,5));
		JPanel p2s = new JPanel();
		JPanel p2ss = new JPanel();
		p2.add(p2s);
		sel1 = new JButton("1. 나의 검진결과 등록하기");
		sel1.setFont(font3);
		sel2 = new JButton("2. 결과 분석 보기");
		sel2.setFont(font3);
		sel3 = new JButton("3. 질병 탐색하기");
		sel3.setFont(font3);
		sel4 = new JButton("4. 개인정보 수정하기");
		sel4.setFont(font3);
		sel5 = new JButton("5. 로그아웃");
		sel5.setFont(font3);
		p2.add(sel1);
		p2.add(sel2);
		p2.add(sel3);
		p2.add(sel4);
		p2.add(sel5);
		p2.add(p2ss);
		p1.add(p2);
		p.add(p1);
		
		this.add(p);
		this.setBounds(300,300,0,0);
		setSize(500,370);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		revalidate();
	    repaint();
	    
	    sel1.addActionListener(this);
	    sel2.addActionListener(this);
	    sel3.addActionListener(this);
	    sel4.addActionListener(this);
	    sel5.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sel1) {
			setVisible(false);
			HealthCheckFrame h = new HealthCheckFrame(memberdto);
		}
		if(e.getSource() == sel5) {
			setVisible(false);
			MainFrame m = new MainFrame();
		}
		if(e.getSource() == sel2) {
			new resultFramemain(memberdto);
			setVisible(false);
		}
	}
}
