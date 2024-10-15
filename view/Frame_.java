package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public interface Frame_ {
				// 프레임별로 고정될 머리글 바닥글을 인터페이스로 구현받도록 설계
	Font font = new Font("Helvetica", Font.BOLD|Font.PLAIN, 15);
	Font font2 = new Font("Helvetica", Font.BOLD, 30);
	public JPanel title_p = new JPanel();
	public JLabel title = new JLabel(" 나의 건강 지킴이 ");
	public JPanel bottom_p = new JPanel();
	public JLabel bottom = new JLabel(" 개발 : 박연정    ",JLabel.RIGHT);
}
