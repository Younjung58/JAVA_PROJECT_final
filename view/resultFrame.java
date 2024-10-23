package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.diseaseDAO;
import dao.healthNormalDAO;
import dao.preventDiseaseDAO;
import dto.HealthDTO;
import dto.HealthNormalDTO;
import dto.MemberDTO;
import dto.healthDiseaseDTO;
import dto.preventDiseaseDTO;

public class resultFrame extends JFrame implements Frame_,ActionListener{
	
	private HealthDTO healthdto = null;
	private healthNormalDAO normal = healthNormalDAO.getInstance();
	private diseaseDAO disease = diseaseDAO.getInstance();
	private preventDiseaseDAO prevent = preventDiseaseDAO.getInstance();
	private MemberDTO memberdto = null;
	
	private ArrayList<String> diseaseList;
	private String [] diseaseList1;
	private String a = " 항목 : ";
	private float BMI;
	private int AC, SBP, DBP, FBG, TC, HDL, TG, LDL, AST, ALT;
	private String gender;
	private String pr = "예방단계 진단";
	
	private JButton cancel;
	private JButton back;
	
	public resultFrame(HealthDTO healthdto, MemberDTO memberdto) {
		this.memberdto = memberdto;
		this.healthdto = healthdto;
		
		this.setBounds(300, 300, 600, 450);
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
		
		
		
		// 중간 내용
	    JPanel panel = new JPanel(new GridLayout(2,1));
	    JPanel panel1 = new JPanel(new GridLayout(4,1));
	    JPanel panel1s = new JPanel();
	    JPanel panel2s = new JPanel();
	    JPanel panel1ss = new JPanel();
	    JPanel panel2ss = new JPanel();
	    JLabel j1 = new JLabel();
	    j1.setText("나의 비정상수치");
	    j1.setFont(font3);
	    panel1ss.add(j1);
	    JTextField t1 = new JTextField();
	    t1.setSize(200,30);
	    panel1s.add(panel1ss);
	    panel1s.add(t1);
	    panel1.add(panel1s);
	    
	    JLabel j2 = new JLabel();
	    j2.setText("Comment 확인할 항목 선택");
	    j2.setFont(font3);
	    diseaseList = this.getAbnormal(healthdto);
	    int n = diseaseList.size();
	    diseaseList1 = new String[n+1];
	    panel2ss.add(j2);
	    panel2s.add(panel2ss);
	  
	    for (int i = 0; i < n ; i++) {
			diseaseList1[i] = diseaseList.get(i);
//			System.out.println();
			a += (diseaseList.get(i));
			if(i!=n-1) {
				a+=" / ";
			}
	    }
	    diseaseList1[n] = pr;
	    
	    if(diseaseList.size()==0) {
	    	a+=" 해당 없음";
	    }
	    t1.setText(a);	    
	    
	    t1.setEditable(false);
	    t1.setForeground(Color.red);
		JComboBox<String> diseaseCombo = new JComboBox<String>(diseaseList1);
		panel2s.add(diseaseCombo);
		panel1.add(panel2s);
		
		JPanel bt = new JPanel();
		cancel = new JButton("취소");
		back = new JButton("뒤로가기");
		cancel.setSize(40,30);
//		cancel.setLocation(20,20);
		bt.add(back);
		bt.add(cancel);
//		bt.setBounds(100,500,20,20);
//		this.add(bt);
	    panel1.add(bt);
		
		panel.add(panel1);
		
		JTextArea ta = new JTextArea();
		JScrollPane jsp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(20,160,550,200);
		ta.setBackground(Color.LIGHT_GRAY);
		ta.setEditable(false);
		this.add(jsp);
//		jsp.getVerticalScrollBar().setValue(1);
//		ta.setCaretPosition(0);
		this.add(panel,"Center");
		revalidate();
		repaint();
//		
		
//		panel.add(bt);
		revalidate();
		repaint();
		
		cancel.addActionListener(this);
		back.addActionListener(this);
	    
		diseaseCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String b = "--------------------------------------------------------------------------\n";
				String d = ("--------------🏥 💊💊💊🏥 --------------"+"\n");
				JComboBox<String> type = (JComboBox)e.getSource();
				Object n = type.getSelectedItem();
				if(n.equals(pr)) {
					preventDiseaseDTO preventdto  = new preventDiseaseDTO();
					
					if(healthdto.getCf().equals("N") && (diseaseList.size()==0)) {
						preventdto = prevent.selectOne("1차 예방 (질병 전단계)");
					}else if(healthdto.getCf().equals("N") && (diseaseList.size()!=0)) {
						preventdto = prevent.selectOne("2차 예방 (질병 잠복기)");
					}else if(healthdto.getCf().equals("Y") && (diseaseList.size()!=0)) {
						preventdto = prevent.selectOne("3차 예방 (질병 발현기)");
					}else if(healthdto.getCf().equals("Y") && (diseaseList.size()==0)) {
						preventdto = prevent.selectOne("4차 예방");
					}
					String t = preventdto.toString();
					ta.setText(t);
					ta.setFont(font3);
					ta.setLineWrap(true);
					
				}else {
					String d1;
//				    System.out.println(n);
					healthDiseaseDTO healthdiseasedto = new healthDiseaseDTO();
					if(n.equals("AC") && healthdto.getGender().equals("남")) {
						healthdiseasedto = disease.selectOne("mxACm");
					}else if(n.equals("AC") && healthdto.getGender().equals("여")) {
						healthdiseasedto = disease.selectOne("mxACw");
					}else if((n.equals("SBP") && SBP > normal.selectOne("SBP").getMax())|| (n.equals("DBP") && DBP > normal.selectOne("DBP").getMax())) {
						healthdiseasedto = disease.selectSBP_DBP((String)n,"고혈압");
					}else if((n.equals("SBP") && SBP < normal.selectOne("SBP").getMin())|| (n.equals("DBP") && DBP < normal.selectOne("DBP").getMin())) {
						healthdiseasedto = disease.selectSBP_DBP((String)n,"저혈압");
					}else{
						healthdiseasedto = disease.selectOne((String)n);					
					}
					
					if(healthdiseasedto.getType().equals("BMI")) {
						d1 = ("관련항목 : " + healthdiseasedto.getType()+" ( 나의 BMI수치 : "+BMI+" ) \n"+b);
					}else if(healthdiseasedto.getType().equals("mxACm")) {
						d1 = ("관련항목 : AC (남) \n"+b);
					}else if(healthdiseasedto.getType().equals("mxACw")) {
						d1 = ("관련항목 : AC (여) \n"+b);
					}else {
						d1 = ("관련항목 : " + healthdiseasedto.getType()+"\n"+b);					
					}
					String d2 = ("관련 질병명 : " + healthdiseasedto.getName()+"\n"+b);
					String d3 = ("관련 원인 : " + healthdiseasedto.getCause()+"\n"+b);
					String d4 = ("관련 합병증 / 증상 : " + healthdiseasedto.getDisease()+"\n"+b);
					String d5 = ("관리 팁 1 : " + healthdiseasedto.getTip1()+"\n"+b);
					String d6 = ("관리 팁 2 : " + healthdiseasedto.getTip2()+"\n"+b);
					if(healthdiseasedto.getTip2()==null) {
						d6 = "";
					}
					String d7 = ("관리 팁 3 : " + healthdiseasedto.getTip3()+"\n");
					if(healthdiseasedto.getTip3()==null) {
						d7 = "";
					}
					String t = d+d1+d2+d3+d4+d5+d6+d7;
					ta.setText(t);
				    ta.setFont(null);
					ta.setLineWrap(true);
				}
					
			}
				
			
		});
	}
	
	private ArrayList<String> getAbnormal(HealthDTO healthdto){
		ArrayList<String> abnormal = new ArrayList<>();
		HealthNormalDTO normaldto = new HealthNormalDTO();
		this.gender = healthdto.getGender();
		BMI = (float)(healthdto.getWeight() / ((healthdto.getHeight()*0.01)*(healthdto.getHeight()*0.01)));
		normaldto = normal.selectOne("BMI");
		if(BMI > normaldto.getMax()) {
			abnormal.add("BMI");
		}
		this.AC = healthdto.getAC();
		if(gender.equals("남") && AC > normal.selectOne("mxACm").getMax()) {
			abnormal.add("AC");
		}else if(gender.equals("여") && AC > normal.selectOne("mxACw").getMax()) {
			abnormal.add("AC");
		}
		this.SBP = healthdto.getSBP();
		if(SBP > normal.selectOne("SBP").getMax() || SBP < normal.selectOne("SBP").getMin()) {
			abnormal.add("SBP");
		}
		this.DBP = healthdto.getDBP();
		if(DBP > normal.selectOne("DBP").getMax() || DBP < normal.selectOne("DBP").getMin()) {
			abnormal.add("DBP");
		}
		this.FBG = healthdto.getFBG();
		if(FBG > normal.selectOne("FBG").getMax() || FBG < normal.selectOne("FBG").getMin()) {
			abnormal.add("FBG");
		}
		this.TC = healthdto.getTC();
		if(TC > normal.selectOne("TC").getMax()) {
			abnormal.add("TC");
		}
		this.HDL = healthdto.getHDL();
		if(HDL < normal.selectOne("HDL").getMax()) {
			abnormal.add("HDL");
		}
		this.TG = healthdto.getTG();
		if(TG > normal.selectOne("TG").getMax()) {
			abnormal.add("TG");
		}
		this.LDL = healthdto.getLDL();
		if(LDL > normal.selectOne("LDL").getMax()) {
			abnormal.add("LDL");
		}
		this.AST = healthdto.getAST();
		if(AST > normal.selectOne("AST").getMax()) {
			abnormal.add("AST");
		}
		this.ALT = healthdto.getALT();
		if(ALT > normal.selectOne("ALT").getMax()) {
			abnormal.add("ALT");
		}
		
		return abnormal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel) {
			new LogFrame(memberdto);
			this.setVisible(false);
		}
		if(e.getSource() == back) {
			new resultFramemain(memberdto);
			this.setVisible(false);
		}
	}
}
