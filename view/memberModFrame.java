package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import dto.memberDTO;

public class memberModFrame extends JFrame  implements Frame_, ActionListener{
	
	private memberDTO memberdto = null;
	
	private JLabel p1 = new JLabel("비밀번호");
	private JPasswordField pw1 = new JPasswordField(20);	// 비밀번호 가려지는 형식
	
	private JLabel p2 = new JLabel("비밀번호 확인");
	private JPasswordField pw2 = new JPasswordField(20);
	
	private JButton btnCancel;
	private JButton btnSubmit;
	
	public memberModFrame(memberDTO memberdto) {
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
		
		// 내용 생성
		JPanel p = new JPanel();
		p.setLayout(null);
		p1.setBounds(20,25,100,20);
		p2.setBounds(20,50,100,20);
		p.add(p1);
		p.add(p2);
		
		pw1.setBounds(120,25,150,20);
		pw2.setBounds(120,50,150,20);
		
		btnCancel = new JButton("취소");
		btnSubmit = new JButton("수정완료");
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		Panel pButton = new Panel();
		pButton.add(btnCancel);
		pButton.add(btnSubmit);
		pButton.setBounds(120,80,150,40);
		
		p.add(pw1);
		p.add(pw2);
		p.add(pButton);
		
		this.add(p);
		this.setBounds(300,300,380,220);
		setResizable(false);
		setVisible(true);
		revalidate();
	    repaint();
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit) {
			String pw_1 = "";
			char [] pw1c = pw1.getPassword();
			for (char pwcha : pw1c) {
				Character.toString(pwcha);
				// pwcha에 저장된 값을 string의 자료형으로 변환
				pw_1+=pwcha;
			}
			String pw_2 = "";
			char [] pw2c = pw2.getPassword();
			for (char pwcha : pw2c) {
				Character.toString(pwcha);
				// pwcha에 저장된 값을 string의 자료형으로 변환
				pw_2+=pwcha;
			}
			
			if(pw_1.equals(pw_2)) {				
				memberdao.update(pw_2,memberdto.getId());
				JOptionPane.showMessageDialog(null, "비밀번호 변경이 완료되었습니다.","변경 완료",JOptionPane.PLAIN_MESSAGE);
				dispose();
				new MainFrame();
			}else{
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","비밀번호 확인",JOptionPane.WARNING_MESSAGE);
				pw1.setBorder(warn);
				pw1.setText(null);
				pw2.setBorder(warn);
				pw2.setText(null);
			}
		}
		if(e.getSource() == btnCancel) {
			new LogFrame(memberdto);
			this.setVisible(false);
		}
	}
}
