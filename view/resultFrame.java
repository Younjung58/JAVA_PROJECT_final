package view;

import static view.Frame_.font;
import static view.Frame_.title;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	
	private JButton cancel;
	private JButton view;
	
	public resultFrame(MemberDTO memberdto) {
		
		this.setBounds(100, 100, 400, 400);
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
	    frame.setLayout(new GridLayout(4,1));
//	    JPanel framem1 = new JPanel(new GridLayout(3,1));
	    JLabel sstitle = new JLabel(memberdto.getId()+"님 ! 원하시는 항목을 선택하세요.");
	    sstitle.setFont(font3);
	    JPanel frame0 = new JPanel();
	    frame0.add(sstitle);
	    frame.add(frame0);
	    JPanel frame1 = new JPanel();
	    JPanel frame1s1 = new JPanel();
	    resultFrame2 t = new resultFrame2();
	    jtable = t.getTable(memberdto);
	    frame1s1.add(jtable);
	    frame1.add(frame1s1,BorderLayout.CENTER);
		
		JTextField a = new JTextField();
		JTextField b = new JTextField();
		JTextField c = new JTextField();
		
		jtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = jtable.getSelectedRow();
				if(rowIndex != -1) { // -1이 아니면, 선택된 항목을 불러와서 셀값을 읽을 것 
									// 이때 tablemodel의 getValueAt(int rowIndex, int columnlndex)사용)
					TableModel tableModel = jtable.getModel();
					String tempid = (String)memberdto.getId();
					String tempnum = (String)((Integer)tableModel.getValueAt(rowIndex, 1)+"번째 항목");
					a.setText(tempid);
					b.setText(tempnum);
					
					HealthDTO healthdto = new HealthDTO();
					healthdto = healthdao.selectOne((Integer)tableModel.getValueAt(rowIndex, 1), tempid);
					c.setText(healthdto.toString());
				}
			}
		});
		JPanel selectp = new JPanel();
		selectp.setLayout(new GridLayout(4,2));
		selectp.add(new JLabel("[ 선택 사항 ]"));
		selectp.add(new JLabel(""));
		selectp.add(new JLabel("아이디",JLabel.CENTER));
		selectp.add(a);
		selectp.add(new JLabel("항목",JLabel.CENTER));
		selectp.add(b);
		
		
		frame1.add(selectp);
		frame.add(frame1);
//		frame.add(frame1);
//		frame.add(framem1);
		
		JPanel frame2 = new JPanel();
		cancel = new JButton("삭제");
		view = new JButton("결과보기");
		frame2.add(cancel);
		frame2.add(view);
		
		frame.add(frame2);
		
		
		JPanel frame3 = new JPanel();
		frame3.add(c);
		frame.add(frame3);
		
		
		this.add(frame,"Center");
		
		cancel.addActionListener(this);
		view.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cancel) {
			
		}
	}
}
