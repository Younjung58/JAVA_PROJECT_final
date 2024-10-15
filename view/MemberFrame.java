package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class MemberFrame extends JFrame implements Frame_,ActionListener, ChangeListener{
										// 고정된 값이 머리글, 바닥글을 구현받음
	private JPanel center = new JPanel();  // 메인 center
	private JPanel center1 = new JPanel();
	private JPanel center1b = new JPanel();
	private JLabel titlesub = new JLabel(" < 회원가입 > ");
	private JPanel center1b2 = new JPanel();
	private JPanel center2 = new JPanel();
	
	private JPanel center_p = new JPanel();  // 2번째 center구역의 메인 center
	private JPanel name_p = new JPanel();
	private JLabel n = new JLabel("이름");
	private JTextField name = new JTextField();	// 이름 입력
	private JPanel bl1 = new JPanel();	// 공백 1
	private JPanel birth_p = new JPanel();
	private JLabel b = new JLabel("생년월일");
	
	Calendar calendar = Calendar.getInstance();
	private SpinnerDateModel sdm = new SpinnerDateModel();
	private JPanel birth_sp = new JPanel();
	private JPanel birth_spY = new JPanel();
	private JSpinner birthY = new JSpinner();	// 생년월일 입력
	private JPanel birth_spM = new JPanel();
	private JSpinner birthM = new JSpinner();
	private JPanel birth_spD = new JPanel();
	private JSpinner birthD = new JSpinner();
	private JPanel bl2 = new JPanel();	// 공백 2
	
	private JPanel gender_p = new JPanel();
	private JLabel g = new JLabel("성별");
	private JPanel gender = new JPanel();
	private JRadioButton gender1 = new JRadioButton("남");	// 성별 선택
	private JRadioButton gender2 = new JRadioButton("여");
	private JPanel bl3 = new JPanel();
	
	private JPanel id_p = new JPanel();
	private JLabel i = new JLabel("아이디");
	private JTextField id = new JTextField();	// 아이디 입력
	private JPanel idb1 = new JPanel();
	private JPanel idb2 = new JPanel();
	
	private JPanel pw_p1 = new JPanel();
	private JLabel p1 = new JLabel("비밀번호");
	private JPasswordField pw1 = new JPasswordField();	// 비밀번호 가려지는 형식
	private JPanel pwb1 = new JPanel();
	
	private JPanel pw_p2 = new JPanel();
	private JLabel p2 = new JLabel("비밀번호 확인");
	private JPasswordField pw2 = new JPasswordField();
	private JPanel pwb2 = new JPanel();
	
	private JButton idcf = new JButton("ID \n 중복확인");
	private JButton commit = new JButton("등록");
	private JButton cancel = new JButton("취소");
	
	Container con = this.getContentPane();
	
	public MemberFrame() {
		
		this.setVisible(true);
		this.setBounds(100, 100, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
//		center.setLayout(new GridLayout(3,1));
		
		// 중간 내용 추가
		center_p.setLayout(new GridLayout(7,3));
		// 0번째줄
		center_p.add(center1b);
		center1.add(titlesub);
		titlesub.setFont(font2);
		center_p.add(center1);
		center_p.add(center1b2);
		// 첫번째줄
		name_p.add(n);
		center_p.add(name_p);
		center_p.add(name);	// 이름 입력 - name
		center_p.add(bl1);
		// 두번째줄
		birth_p.add(b);
		center_p.add(birth_p);
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
		
		center_p.add(birth_sp);		//	스피너 형성후 추가
		center_p.add(bl2);
		// 세번째줄
		gender_p.add(g);
		center_p.add(gender_p);
		gender.setLayout(new GridLayout(1,2));
		gender1.setFont(font);
		gender2.setFont(font);
		ButtonGroup group = new ButtonGroup();
		group.add(gender1);
		group.add(gender2);
		gender.add(gender1);
		gender.add(gender2);
		center_p.add(gender);
		center_p.add(bl3);
		// 네번째줄
		id_p.add(i);
		center_p.add(id_p);
		center_p.add(id);		//	아이디 입력 - id
		JPanel id1 = new JPanel();
		id1.setLayout(new GridLayout(1,4));
		idcf.setPreferredSize(new Dimension(100,25));
		id1.add(idb1);
		id1.add(idcf);
		id1.add(idcf);
		id1.add(idb2);
		center_p.add(id1);
		// 다섯번째줄
		pw_p1.add(p1);
		center_p.add(pw_p1);
		center_p.add(pw1);
		center_p.add(pwb1);
		// 여섯번째줄
		pw_p2.add(p2);
		center_p.add(pw_p2);
		center_p.add(pw2);
		center_p.add(pwb2);
		
		
		
//		center.add(center1);
		center.add(center_p);

		
		this.add(center,"Center");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
