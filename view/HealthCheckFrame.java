package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto.HealthDTO;
import dto.MemberDTO;

public class HealthCheckFrame extends JFrame implements Frame_,ActionListener{
	
	MemberDTO memberdto = null;
	
	private JLabel title_s,title_sub1,title_sub2, heightl,weightl,ACl,BP,SBPl,DBPl,BG,FBGl,
					C,TCl,HDLl,TGl,LDLl,A,ASTl,ALTl,cfl;
	private JTextField height,weight,AC,SBP,DBP,FBP,TC,HDL,TG,LDL,
						AST,ALT;
	
	private JButton btnSubmit,btnCancel;
	
	
	
	String id;		//나중에 넘겨 받을 값임
	
	Container con = this.getContentPane(); 
	
	
	
	public HealthCheckFrame(MemberDTO memberdto) {
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
		title_s = new JLabel("< 검진 결과 등록하기 >");
		title_sub1 = new JLabel(" ' " +memberdto.getId()+" 님 ( "+memberdto.getGender()+" ) '");
		title_sub2 = new JLabel("--- 입력하지 않은 칸은 기본 정상값으로 등록됩니다 ---");
		heightl = new JLabel("키(cm)");
		weightl = new JLabel("몸무게(kg)");
		ACl = new JLabel("복부둘레(cm)");
		BP = new JLabel("혈압(mmHg)  :  ");
		SBPl = new JLabel("수축기");
		DBPl = new JLabel("이완기");
		BG = new JLabel("혈당(mg/dL)  :  ");
		FBGl = new JLabel("공복시");
		C = new JLabel("콜레스테롤(mg/dL)  :  ");
		TCl = new JLabel("총콜레스테롤");
		HDLl = new JLabel("HDL");
		TGl = new JLabel("중성지방");
		LDLl = new JLabel("LDL");
		A = new JLabel("간(IU/L)  :  ");
		ASTl = new JLabel("AST");
		ALTl = new JLabel("ALT");
		cfl = new JLabel("상태 (Y / N)    -    검진전 몸상태 이상의 증상을 느꼈나요 ? ");
		
		title_s.setBounds(20,15,300,20);
		title_s.setFont(font2);
		title_sub1.setBounds(300,15,150,20);
		title_sub1.setFont(font4);
		title_sub1.setForeground(Color.DARK_GRAY);
		title_sub2.setBounds(80, 40, 300, 20);
		title_sub2.setForeground(Color.red);
		heightl.setBounds(20,70,100,20);
		heightl.setFont(font3);
		weightl.setBounds(170,70,100,20);
		weightl.setFont(font3);
		ACl.setBounds(320,70,100,20);
		ACl.setFont(font3);
		BP.setBounds(20,110,150,20);
		BP.setFont(font3);
		SBPl.setBounds(150,110,100,20);
		SBPl.setFont(font3);
		DBPl.setBounds(320,110,100,20);
		DBPl.setFont(font3);
		BG.setBounds(20,150,100,20);
		BG.setFont(font3);
		FBGl.setBounds(150,150,100,20);
		FBGl.setFont(font3);
		C.setBounds(20,190,150,20);
		C.setFont(font3);
		TCl.setBounds(180,190,100,20);
		TCl.setFont(font3);
		HDLl.setBounds(370,190,100,20);
		HDLl.setFont(font3);
		TGl.setBounds(180,230,100,20);
		TGl.setFont(font3);
		LDLl.setBounds(370,230,100,20);
		LDLl.setFont(font3);
		A.setBounds(20,270,150,20);
		A.setFont(font3);
		ASTl.setBounds(150,270,100,20);
		ASTl.setFont(font3);
		ALTl.setBounds(320,270,100,20);
		ALTl.setFont(font3);
		cfl.setBounds(20,310,370,20);
		cfl.setFont(font3);
		p.add(title_s);
		p.add(title_sub1);
		p.add(title_sub2);
		p.add(heightl);
		p.add(weightl);
		p.add(ACl);
		p.add(BP);
		p.add(SBPl);
		p.add(DBPl);
		p.add(BG);
		p.add(FBGl);
		p.add(C);
		p.add(TCl);
		p.add(HDLl);
		p.add(TGl);
		p.add(A);
		p.add(LDLl);
		p.add(ASTl);
		p.add(ALTl);
		p.add(cfl);
		
		
		// 내용 입력 칸 추가
		height = new JTextField();
		weight = new JTextField();
		AC = new JTextField();
		SBP = new JTextField();
		DBP = new JTextField();
		FBP = new JTextField();
		TC = new JTextField();
		HDL = new JTextField();
		TG = new JTextField();
		LDL = new JTextField();
		AST = new JTextField();
		ALT = new JTextField();
		
		height.setBounds(70,70,70,25);
		weight.setBounds(250,70,50,25);
		AC.setBounds(415,70,70,25);
		SBP.setBounds(210,110,50,25);
		DBP.setBounds(380,110,50,25);
		FBP.setBounds(210,150,50,25);
		TC.setBounds(280,190,50,25);
		HDL.setBounds(420,190,50,25);
		TG.setBounds(280,230,50,25);
		LDL.setBounds(420,230,50,25); 
		AST.setBounds(210,270,50,25); 
		ALT.setBounds(380,270,50,25);
		p.add(height);
		p.add(weight);
		p.add(AC);
		p.add(SBP);
		p.add(DBP);
		p.add(FBP);
		p.add(TC);
		p.add(HDL);
		p.add(TG);
		p.add(LDL);
		p.add(AST);
		p.add(ALT);
		
		Choice chA = new Choice();
		chA.add("Y");
		chA.add("N");
		chA.setBounds(410, 310, 60, 30);
		
		p.add(chA);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(300,360,60,30);
		p.add(btnCancel);
		btnSubmit = new JButton("결과등록");
		btnSubmit.setBounds(380,360,100,30);
		p.add(btnSubmit);
		
		this.add(p);
		
		this.setBounds(300,300,0,0);
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
		
		setSize(520,500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
//		 height,weight,AC,SBP,DBP,FBP,TC,HDL,TG,LDL,AST,ALT;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnCancel) {
			System.out.println("취소버튼 눌림");
			setVisible(false);
			new LogFrame(memberdto);
		}
		if(e.getSource() == btnSubmit) {
			System.out.println("등록 눌림");
			HealthDTO health = new HealthDTO();
			health.setId(memberdto.getId());
			health.setGender(memberdto.getGender());
			health.setHeight(Integer.valueOf(height.getText()));
			health.setWeight(Integer.valueOf(weight.getText()));
			health.setAC(Integer.valueOf(AC.getText()));
			health.setSBP(Integer.valueOf(SBP.getText()));
			health.setDBP(Integer.valueOf(DBP.getText()));
			health.setFBG(Integer.valueOf(FBP.getText()));
			health.setTC(Integer.valueOf(TC.getText()));
			health.setHDL(Integer.valueOf(HDL.getText()));
			health.setTG(Integer.valueOf(TG.getText()));
			health.setLDL(Integer.valueOf(LDL.getText()));
			health.setAST(Integer.valueOf(AST.getText()));
			health.setALT(Integer.valueOf(ALT.getText()));
//			health.setCf(cf.getText());   // 여기 다시
		}
	}
}
