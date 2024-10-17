package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.memberDAO;
import dto.MemberDTO;

public class MemberFrame extends JFrame implements Frame_, ActionListener, ChangeListener{
										// 고정된 값이 머리글, 바닥글을 구현받음
	memberDAO memberdao = memberDAO.getInstance();
	MemberFrame m = null;
	
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
	
	JButton btnBirth;
	JButton btnCancel;
	JButton btnSubmit;
	
	int year, month, date;
	boolean f = false;
	
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
		
		btnBirth = new JButton("생일등록");
		btnBirth.setBounds(300,75,100,20);
		
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
		idcf.setBounds(300,140,100,25);
		pw1.setBounds(120,170,150,20);
		pw2.setBounds(120,200,150,20);
		
		btnCancel = new JButton("취소");
		btnSubmit = new JButton("가입완료");
		
		btnBirth.addActionListener(this);
		idcf.addActionListener(this);
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
		Panel pButton = new Panel();
		pButton.add(btnCancel);
		pButton.add(btnSubmit);
		pButton.setBounds(120,230,150,40);
		
		
		p.add(name);
		p.add(birth_sp);
		p.add(btnBirth);
		p.add(id);
		p.add(idcf);
		p.add(pGen);
		p.add(pw1);
		p.add(pw2);
		p.add(pButton);
		this.add(p);
		
		this.setBounds(300,300,0,0);
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
		if(e.getSource() == btnBirth) {
			// JSpinner로부터 생일 등록된 값을 받아와서 저장
			// 년
			Calendar select = Calendar.getInstance();
			select.setTime((java.util.Date)birthY.getValue());
			year = (select.get(Calendar.YEAR));
			System.out.println(year);
			// 월
			select.setTime((java.util.Date)birthM.getValue());
			month = (select.get(Calendar.MONTH)+1);
			System.out.println(month);
			// 일
			select.setTime((java.util.Date)birthD.getValue());
			date = (select.get(Calendar.DATE));
			System.out.println(date);
		}
		if(e.getSource() == btnSubmit) {
			System.out.println("등록 눌림");
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
//			System.out.println(pw_1+"---1번");
//			System.out.println(pw_2+"---2번");
			if(pw_1.equals(pw_2)&&f) {				
//				System.out.println(name.getText());
//				this.stateChanged(null);
				MemberDTO memberdto= new MemberDTO();
//				System.out.println(year+"-"+month+"-"+date);
				memberdto.setName(name.getText());
				memberdto.setBirth(year+"-"+month+"-"+date);
				String gen = null;
				for (int i = 0; i < gender.length; i++) {
					if(gender[i].isSelected()) {
						gen = gender[i].getText();
					}
				}
				memberdto.setGender(gen);
				memberdto.setId(id.getText());
//				System.out.println(gen);
//				System.out.println(id.getText());
//				System.out.println(pw_2);
				memberdto.setPw(pw_2);
				memberdao.add(memberdto);
				JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.","등록 완료",JOptionPane.PLAIN_MESSAGE);
				dispose();
				new MainFrame();
			}else if(f==false) {
				JOptionPane.showMessageDialog(null, "아이디 중복 체크를 진행해주세요.","아이디 체크",JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","비밀번호 확인",JOptionPane.WARNING_MESSAGE);
//				dispose();
//				new MemberFrame(name.getText(),id.getText());
			}
		}
		if(e.getSource() == idcf) {
			ArrayList<MemberDTO> list = new ArrayList<>();
			list = memberdao.selectAll();
			boolean flag = true;
//			System.out.println(id.getText());
//			for(MemberDTO m : list) {
//				System.out.println(m.getId()+" --등록값");
//			}
			if(list!=null) {
				for(MemberDTO m : list) {
					if(m.getId().equals(id.getText())) {
						JOptionPane.showMessageDialog(null, "해당 아이디는 이미 존재합니다.","아이디 중복",JOptionPane.WARNING_MESSAGE);
//					dispose();
//					new MemberFrame(name.getText(),null);
						flag = false;
						break;
					}
				}				
			}
			if(flag||list==null) {
				JOptionPane.showMessageDialog(null, id.getText()+"는 사용 가능한 아이디입니다.","사용 가능",JOptionPane.PLAIN_MESSAGE);
				f = true;		// 중복검사 진행 확인
//				dispose();
//				new MemberFrame(name.getText(),id.getText());
			}
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {

	}

}
