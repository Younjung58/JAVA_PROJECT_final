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
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.HealthDTO;
import dto.MemberDTO;

public class resultFrame extends JFrame implements Frame_, ActionListener{
	
	Dimension dim = new Dimension(400,300);
	Container con = this.getContentPane();
	
	private Object[] colunms = new Object[] {"검사결과항목","선택","수정"};
	private Object[][] contents;
	private JFrame j;
	private DefaultTableModel table;
	private JTable jtable;
	private JScrollPane jsp;
	
	public resultFrame(MemberDTO memberdto) {
		
		this.setBounds(100, 100, 800, 700);
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
		
		revalidate();
	    repaint();
		
		// 중간 내용 정리
	    JPanel frame = new JPanel();
//	    frame.setBackground(Color.red);
//	    JScrollPane frame1 = new JScrollPane();
//	    JLabel title1 = new JLabel("나의 등록내용");
//	    title1.setFont(font);
//	    frame.add(title1);
//	    frame.setBackground(Color.yellow);
//	    frame.setLocation(0,35);
//	    frame.setSize(400,30);
//	    frame1.setPreferredSize(dim);
//	    frame.setLayout(null); 
//	    frame1.add(title1);
//	    frame.add(title1);
//	    this.add(frame);
	    
	    JPanel frame2 = new JPanel();
	    String header [] = {"아이디","등록번호","선택"};	// 테이블 머리
//		contents = new Object[][] {{1,2,3},{4,5,6}};	// 테이블 내용목록
		table = new DefaultTableModel(contents, header);
		jtable = new JTable(table);	// 틀에 테이블 올리고
		jsp = new JScrollPane(jtable); // 테이블 넘겨주기
		ArrayList<HealthDTO> list = healthdao.selectAll(memberdto.getId());
		int n = list.size();
		
		JRadioButton [] select = new JRadioButton[n];
		ButtonGroup selgg = null;
		String id;
		int no;
		for(HealthDTO h : list) {
			id=h.getId();
			no=h.getNo();
			select[no-1] = new JRadioButton(" ");
			selgg.add(select[no-1]);
			select[no-1].addActionListener(this);
		}
		no = 1;
		for (int i = 0; i < select.length; i++) {
			table.addRow(new Object[] {memberdto.getId(),no,select[i]});
		}
		jsp.setLocation(0,32);
		jsp.setSize(400,550);
		
		
		
		
		
		
		
		JButton cancel = new JButton("삭제");
		
		
		
		JButton sel = new JButton("결과보기");
		
		
		
//		frame2.add(jsp);
//		frame.add(frame2);
		this.add(jsp);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
