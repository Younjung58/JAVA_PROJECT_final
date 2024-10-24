package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.Color;
import java.awt.Dimension;
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

import dao.diseaseDAO;
import dto.memberDTO;
import dto.healthDiseaseDTO;

public class diseaseFrame extends JFrame implements Frame_,ActionListener{
	
	private healthDiseaseDTO healthdiseasedto = null;
	private memberDTO memberdto = null;
	private diseaseDAO diseasedao = diseaseDAO.getInstance();
	
	private ArrayList<String> diseaseList;
	private String [] diseaseList1;
	private ArrayList<healthDiseaseDTO> diseaselist;
	private ArrayList<String> disease;
	
	private JButton cancel;
	private JButton back;
	String d1,d2,d3,d4,d5,d6,d7;
	
	
	public diseaseFrame(memberDTO memberdto) {
		this.memberdto = memberdto;
		this.setBounds(300, 300, 600, 400);
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
		
		
		// 중간 내용 추가
		JPanel panel = new JPanel();
		
		JPanel panel1 = new JPanel();
		JLabel j1 = new JLabel();
	    j1.setText("병명 목록");
	    j1.setFont(font3);
	    panel1.add(j1);
	    panel1.setBounds(50, 33, 80, 40);
	    panel.add(panel1);
	    
		
	    diseaseList = this.getDisease();
		int n = diseaseList.size();
		  diseaseList1 = new String[n];
		for (int i = 0; i < n ; i++) {
			diseaseList1[i] = diseaseList.get(i);
	    }
		
		JPanel panel2 = new JPanel();
		JComboBox<String> diseaseCombo = new JComboBox<String>(diseaseList1);
		panel2.add(diseaseCombo);
		panel2.setBounds(60, 60, 30, 20);
		panel.add(panel2);
		revalidate();
		repaint();
		
		JTextArea ta = new JTextArea();
		JScrollPane jsp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(new Dimension(420,250));
//		ta.setBounds(20,160,200,200);
		jsp.setBounds(20,100,200,200);
		ta.setBackground(Color.LIGHT_GRAY);
		ta.setEditable(false);
		panel.add(jsp);
		revalidate();
		repaint();
		
		
		JPanel bt = new JPanel();
//		cancel = new JButton("취소");
		back = new JButton("뒤로가기");
//		bt.add(cancel);
		bt.add(back);
		bt.setPreferredSize(new Dimension(100,50));
		panel.add(bt);
		
		this.add(panel,"Center");
		
		back.addActionListener(this);
		
		
		diseaseCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String b = "--------------------------------------------------------------------------\n";
				JComboBox<String> type = (JComboBox)e.getSource();
				Object n = type.getSelectedItem();
				ArrayList<healthDiseaseDTO> healthdiseaselist = diseasedao.selectDisease((String)n);
				String total = "";
				int i = 0;
				int j = 1;
				String d = ("---------------🙆🏻‍♀️🙆🏻‍♀️🙆🏻‍♀️---------------"+"\n");
				String t = (i+1)+"번째.";
				d2 = "관련 건강검진 항목 : ";
				for(healthDiseaseDTO h : healthdiseaselist) {
					d2 += (i+1)+". "+healthdiseaselist.get(i).getType()+"\t";
					if(j == 1) {
						d1 = ("관련 질병명 : " + healthdiseaselist.get(i).getName()+"\n"+b);
						d3 = ("관련 원인 : " + healthdiseaselist.get(i).getCause()+"\n"+b);
						d4 = ("관련 합병증 : " + healthdiseaselist.get(i).getDisease()+"\n"+b);
						d5 = ("관리 팁 1 : " + healthdiseaselist.get(i).getTip1()+"\n"+b);
						d6 = ("관리 팁 2 : " + healthdiseaselist.get(i).getTip2()+"\n"+b);
						if(healthdiseasedto.getTip2()==null) {
							d6 = "";
						}
						d7 = ("관리 팁 3 : " + healthdiseasedto.getTip3());
						if(healthdiseasedto.getTip3()==null) {
							d7 = "";
						}
					}
					i++;
					j++;
				}
				t = d+d1+d2+b+d3+d4+d5+d6+d7;
				total += (t+"\n");
				
				ta.setText(total);
			    ta.setFont(null);
				ta.setLineWrap(true);
			}
				
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back) {
			new LogFrame(memberdto);
			this.setVisible(false);
		}
	}
	
	private ArrayList<String> getDisease(){
		disease = new ArrayList<>();
		healthdiseasedto = new healthDiseaseDTO();
		diseaselist = diseasedao.selectAll();
		int i = 0;
		String a = null;
		for(healthDiseaseDTO h : diseaselist) {
			a= diseaselist.get(i).getName();
			if(doubleCheck(a)==false) {		// 중복사항 체크해서 추가 제한
				disease.add(a);					
			}
			i++;
		}
		return disease;
	}
	
	private boolean doubleCheck(String a) {
		for(int i = 0 ; i < disease.size() ; i++) {
			if(a.equals(disease.get(i))) {
				return true;				
			}
		}
		return false;
	}
	
}
