package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.memberDAO;

public class MemberFrame extends JFrame implements Frame_, ActionListener, ChangeListener{
										// 고정된 값이 머리글, 바닥글을 구현받음
	memberDAO memberdao = memberDAO.getInstance();
	
	private JLabel titlesub = new JLabel(" < 회원가입 > ");

	private JLabel n = new JLabel("이름");
	private JTextField name = new JTextField(20);	// 이름 입력
	
	private JLabel b = new JLabel("생년월일");
	
	Calendar calendar = Calendar.getInstance();
	private JPanel birth_sp = new JPanel();
	private JPanel birth_spY = new JPanel();
	private JSpinner birthY = new JSpinner();	// 생년월일 입력
	private JPanel birth_spM = new JPanel();
	private JSpinner birthM = new JSpinner();
	private JPanel birth_spD = new JPanel();
	private JSpinner birthD = new JSpinner();
	 
	private JLabel g = new JLabel("성별");
	private JRadioButton [] gender = new JRadioButton[2];
	private ButtonGroup gg;
	
	private JLabel i = new JLabel("아이디");
	private JTextField id = new JTextField();	// 아이디 입력
	
	private JLabel p1 = new JLabel("비밀번호");
	private JPasswordField pw1 = new JPasswordField(20);	// 비밀번호 가려지는 형식
	
	private JLabel p2 = new JLabel("비밀번호 확인");
	private JPasswordField pw2 = new JPasswordField(20);
	
	private JButton idcf = new JButton("ID \n 중복확인");
	
	JButton btnCancel;
	JButton btnSubmit;
	
	Container con = this.getContentPane();
	
	public MemberFrame(){
		
		
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
		
		// 내용생성
		JPanel p = new JPanel();
		p.setLayout(null);
		titlesub.setBounds(138,15,120,20);
		n.setBounds(20,50,100,20);
		b.setBounds(20,80,100,20);
		g.setBounds(20,110,100,20);
		i.setBounds(20,140,100,20);
		p1.setBounds(20,170,100,20);
		p2.setBounds(20,200,100,20);
		
		titlesub.setFont(font2);
		p.add(titlesub);
		p.add(n);
		p.add(b);
		p.add(g);
		p.add(i);
		p.add(p1);
		p.add(p2);

		
		  // 년도 JSpinner
		birthY.addChangeListener(this);
		birthY.setModel(new SpinnerDateModel(calendar.getTime(),null,null,Calendar.YEAR));
											// 표기할 날짜값, 최소, 최대, 바꿀부분
		birthY.setEditor(new JSpinner.DateEditor(birthY,"yyyy"));
		birth_spY.add(birthY);
		birth_sp.add(birth_spY);
		  // 월 JSpinner
		birthM.addChangeListener(this);
		birthM.setModel(new SpinnerDateModel(calendar.getTime(),null,null,Calendar.MONTH));
											// 표기할 날짜값, 최소, 최대, 바꿀부분
		birthM.setEditor(new JSpinner.DateEditor(birthM,"MM"));
		birth_spM.add(birthM);
		birth_sp.add(birth_spM);
		  // 일 JSpinner
		birthD.addChangeListener(this);
		int day = Calendar.DAY_OF_MONTH;
		birthD.setModel(new SpinnerDateModel(calendar.getTime(),null,null,day));
											// 표기할 날짜값, 최소, 최대, 바꿀부분
		birthD.setEditor(new JSpinner.DateEditor(birthD,"dd"));
		birth_spD.add(birthD);
		birth_sp.add(birth_spD);
		
		birth_sp.setBounds(95,70,200,50);
		
		JPanel pGen = new JPanel();
		
		gg = new ButtonGroup();
		gender[0] = new JRadioButton("남");
		gender[1] = new JRadioButton("여");
		for (int i = 0; i < gender.length; i++) {
			gg.add(gender[i]);
			pGen.add(gender[i]);
			gender[i].addActionListener(this);;
		}
		pGen.setBounds(120,110,100,30);
		
		name.setBounds(120,50,150,20);
		id.setBounds(120,140,150,20);
		idcf.setBounds(300,140,100,30);
		pw1.setBounds(120,170,150,20);
		pw2.setBounds(120,200,150,20);
		
		btnCancel = new JButton("취소");
		btnSubmit = new JButton("가입완료");
		
		idcf.addActionListener(this);
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
		Panel pButton = new Panel();
		pButton.add(btnCancel);
		pButton.add(btnSubmit);
		pButton.setBounds(120,230,150,40);
		
		
		p.add(name);
		p.add(birth_sp);
		p.add(id);
		p.add(idcf);
		p.add(pGen);
		p.add(pw1);
		p.add(pw2);
		p.add(pButton);
		this.add(p);
		
		
		setSize(430,370);
		setResizable(false);
		setVisible(true);
		revalidate();
	    repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCancel) {
			System.out.println("취소버튼 눌림");
			setVisible(false);
			new MainFrame();
		}
		if(e.getSource() == btnSubmit) {
			System.out.println("등록 눌림");
			
			
		}
		if(e.getSource() == idcf) {
			
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
