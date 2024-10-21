package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import dto.HealthDTO;
import dto.MemberDTO;

public class resultFramesub extends JFrame implements Frame_,ActionListener,ItemListener{
	
	private JTable jtable;
//	private Object[][] header;
	private Object[][] contents;
	int n;
	
	public resultFramesub() {
		// TODO Auto-generated constructor stub
	}

	public JTable getTable(MemberDTO memberdto) {
		if(jtable == null) {
			String [] columnNames = {"아이디", "항목", "선택"};
//			DefaultTableModel table = new DefaultTableModel(contents, header);
			ArrayList<HealthDTO> list = healthdao.selectAll(memberdto.getId());
			n = list.size();
			JCheckBox [] select = new JCheckBox[n];
			Object [][] rowData= new Object[n][3];
			for (int i = 0; i < n; i++) {
					select[i] = new JCheckBox("선택");
					rowData[i][0] = memberdto.getId()+"님";
					rowData[i][1] =(i+1);
					rowData[i][2] = select[i];
					select[i].setBorderPainted(true);
					select[i].addItemListener(this);
			}
			jtable = new JTable(rowData, columnNames);
			
			TableColumn name = jtable.getColumn("아이디");
			name.setCellRenderer(new setNameTableCellRender());
			TableColumn num = jtable.getColumn("항목");
			num.setCellRenderer(new setNumTableCellRender());
			TableColumn sel = jtable.getColumn("선택");
			sel.setCellRenderer(new setSelTableCellRender());
		}
		return jtable;
	}
	
	
	
	private class setNameTableCellRender extends JLabel implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
				// TODO Auto-generated method stub
				setText(value.toString());		// "아이디"컬럼의 항목가져옴
				setFont(new Font(null,Font.PLAIN,12));		// 글씨지정
				setHorizontalAlignment(JLabel.CENTER);		//중앙정렬
				
				if(isSelected) {
					setBackground(Color.yellow);
				}else {
					setBackground(Color.white);
				}
				
				return this;		// 설정완료된 component return
			}
		}
		
	private class setNumTableCellRender extends JLabel implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
				// TODO Auto-generated method stub
				setText(value.toString());		// "항목"컬럼의 항목가져옴
				setFont(new Font(null,Font.PLAIN,10));		// 글씨지정
				setHorizontalAlignment(JLabel.CENTER);		//중앙정렬
				
				if(isSelected) {
					setBackground(Color.yellow);
				}else {
					setBackground(Color.white);
				}
				
				return this;		// 설정완료된 component return
			}
		}

	private class setSelTableCellRender extends JCheckBox implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
			// TODO Auto-generated method stub
//			Boolean bool = (Boolean)value;
//			setSelected(bool.booleanValue());
//			ButtonGroup g = new ButtonGroup();
//			JRadioButton [] sel = new JRadioButton[n];
//			for (int i = 0; i < n; i++) {
//				sel[i] = value
//			}
//			setFont(new Font(null,Font.PLAIN,10));		// 글씨지정
			setHorizontalAlignment(CENTER);		//중앙정렬
			
			if(isSelected) {
				setSelected(true);
				setBackground(Color.yellow);
			}else {
				setSelected(false);
				setBackground(Color.white);
			}
			
			return this;		// 설정완료된 component return
		}
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int a;
		if(e.getStateChange() == ItemEvent.SELECTED) {
			
		}
	}


	
	
}
