package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDTO;
		// CRUD 작업을 하는 memberDAO는 객체 하나만 생성해서 사용해도 되기에 싱글톤 기법사용
		// 객체가 무한정으로 생성되는 것을 방지하고, 프로그램실행시 하나의 클래스 객체만 생성되는 것을 보장
public class memberDAO extends oracleload implements DBdao_member{
					// 오라클과 연동이 필요한 작업에 대한 부분을 상속받아 부모 메서드를 사용하여 진행
	public static memberDAO memberdao = null;
	
	private memberDAO(){
		load();
		// 객체가 생성될 때 오라클 드라이버 로드의 과정 진행(초기 1번)
	}
	public static memberDAO getInstance() {
		if(memberdao == null) {
			memberdao = new memberDAO();
		}
		return memberdao; 
	}
	@Override
	public void add(MemberDTO memberdto) {
		// TODO Auto-generated method stub
		if(conn()) {
			try {
				String sql = "insert into member values(?,?,?,?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, memberdto.getName());
				psmt.setString(2, memberdto.getBirth());
				psmt.setString(3, memberdto.getGender());
				psmt.setString(4, memberdto.getId());
				psmt.setString(5, memberdto.getPw());
				
				int result = psmt.executeUpdate();
				
				if(result > 0) {
					conn.commit();
					System.out.println("회원 등록 완료");
					
				}else {
					conn.rollback();
					System.err.println("단어 등록에 실패하였습니다. --에러");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {		// conn() 메소드 실행결과 false를 리턴받았다면 == 커넥션 자원을 가져오지 못함을 의미
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
	}
	@Override
	public ArrayList<MemberDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<MemberDTO> memberlist = new ArrayList<>();
		if(conn()) {
			try {
				String sql = "select * from member";
				PreparedStatement psmt = conn.prepareStatement(sql);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					MemberDTO memberdto = new MemberDTO();
					memberdto.setName(rs.getString("name"));
					memberdto.setBirth(rs.getString("birth"));
					memberdto.setGender(rs.getString("gender"));
					memberdto.setId(rs.getString("id"));
					memberdto.setPw(rs.getString("pw"));
					memberlist.add(memberdto);
				}
				return memberlist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
		return null;
	}
	@Override
	public void update(String pw, String id) {
		if(conn()) {
			try {
				String sql = "update member set pw = ? where id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, pw);
				psmt.setString(2, id);
				
				int result = psmt.executeUpdate();
				
				if(result>0) {
					conn.commit();
					System.out.println("해당 내용으로 회원 수정이 완료되었습니다.");
				}else {
					conn.rollback();
					System.err.println("회원 수정에 실패하였습니다. --에러");
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {		// conn() 메소드 실행결과 false를 리턴받았다면 == 커넥션 자원을 가져오지 못함을 의미
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
	}
	@Override
	public void delete(String id) {
		if(conn()) {
			try {
				String sql = "delete from member where id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				
				int result = psmt.executeUpdate();
				
				if(result > 0) {
					conn.commit();
					System.out.println("회원 정보가 삭제되었습니다.");
				}else {
					conn.rollback();
					System.err.println("삭제에 실패하였습니다. --에러");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {		// conn() 메소드 실행결과 false를 리턴받았다면 == 커넥션 자원을 가져오지 못함을 의미
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
	}
	@Override
	public MemberDTO selectone(String id) {
		MemberDTO tempdto = new MemberDTO();
		if(conn()) {
			try {
				String sql = "select * from member where id = ?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1,id);
				
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					tempdto.setName(rs.getString("name"));
					tempdto.setBirth(rs.getString("birth"));
					tempdto.setGender(rs.getString("gender"));
					tempdto.setId(rs.getString("id"));
					tempdto.setPw(rs.getString("pw"));
				}
				return tempdto;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.err.println("데이터 커넥션을 실패하였습니다. 다시 시도하세요.");
		}
		return null;
		
	}	
}
