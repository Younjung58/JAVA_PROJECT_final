package dto;

public class healthDiseaseDTO {
	private int no;
	private String type, name, cause, disease, tip1, tip2, tip3;
	
	@Override
	public String toString() {
		return "관련질병 [항목 =" + type + ", 병명 =" + name + ", 원인 =" + cause + ", 관련사항 ="
				+ disease + ", tip1=" + tip1 + ", tip2=" + tip2 + ", tip3=" + tip3 + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getTip1() {
		return tip1;
	}
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}
	public String getTip2() {
		return tip2;
	}
	public void setTip2(String tip2) {
		this.tip2 = tip2;
	}
	public String getTip3() {
		return tip3;
	}
	public void setTip3(String tip3) {
		this.tip3 = tip3;
	}
	
}
