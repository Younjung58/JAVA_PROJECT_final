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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dto.HealthDTO;
import dto.MemberDTO;

public class resultFramemain extends JFrame implements Frame_, ActionListener{
	
	Dimension dim = new Dimension(400,300);
	Container con = this.getContentPane();
	
	private Object[] colunms = new Object[] {"검사결과항목","선택","수정"};
	private Object[][] contents;
	private JFrame j;
	private DefaultTableModel table;
	private JTable jtable;
	private JScrollPane jsp;
	
	private JButton cancel;
	private JButton delete;
	private JButton view;
	private JTextField a;
	private JTextField b;
	
	private String selid;
	private int selnum;
	MemberDTO memberdto = null;
	HealthDTO healthdto = null;
	
	public resultFramemain(MemberDTO memberdto) {
		this.memberdto = memberdto;
		this.setBounds(300, 300, 480, 380);
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
	    resultFramesub t = new resultFramesub();
	    jtable = t.getTable(memberdto);
	    frame1s1.add(jtable);
	    frame1.add(frame1s1,BorderLayout.CENTER);
		
		JTextField a = new JTextField();
		JTextField b = new JTextField();
		JLabel c = new JLabel("",JLabel.CENTER);
		JLabel c1 = new JLabel("",JLabel.CENTER);
		JLabel c2 = new JLabel("",JLabel.CENTER);
		c.setSize(300,100);
		c1.setSize(300,100);
		c2.setSize(300,100);
		jtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int rowIndex = jtable.getSelectedRow();
				if(rowIndex != -1) { // -1이 아니면, 선택된 항목을 불러와서 셀값을 읽을 것 
									// 이때 tablemodel의 getValueAt(int rowIndex, int columnlndex)사용)
					TableModel tableModel = jtable.getModel();
					String tempid = (String)memberdto.getId();
					int tempnum = (Integer)tableModel.getValueAt(rowIndex, 1);
					selid = tempid;
					selnum = tempnum;
					a.setText(tempid);
					b.setText(tempnum+"번째 항목");
					
					healthdto = new HealthDTO();
					healthdto = healthdao.selectOne((Integer)tableModel.getValueAt(rowIndex, 1),tempid);
					c.setText("<현재 등록값> \n");
					c1.setText(healthdto.toString());
					c2.setText(healthdto.toString2());
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
		
		JPanel frame2 = new JPanel((new GridLayout(3,1)));
		frame2.add(c);
		frame2.add(c1);
		frame2.add(c2);
		frame.add(frame2);
		
		JPanel frame3 = new JPanel();
		cancel = new JButton("취소");
		delete = new JButton("삭제");
		view = new JButton("결과보기");
		frame3.add(cancel);
		frame3.add(delete);
		frame3.add(view);
		
		frame.add(frame3);
		
		this.add(frame,"Center");
		
		cancel.addActionListener(this);
		delete.addActionListener(this);
		view.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == delete) {
			System.out.println(selid);
			System.out.println(selnum);
			healthdao.delete(selid , selnum);
			JOptionPane.showMessageDialog(null, "해당 항목 삭제완료 되었습니다.","삭제 완료",JOptionPane.PLAIN_MESSAGE);
			setVisible(false);
			new resultFramemain(memberdto);
		}
		if(e.getSource() == cancel) {
			setVisible(false);
			new LogFrame(memberdto);
		}
		if(e.getSource()==view) {
			setVisible(false);
			new resultFrame(healthdto,memberdto);		//여기부터 !
		}
	}
}