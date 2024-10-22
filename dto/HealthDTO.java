package dto;

public class HealthDTO {
	
	private String id;
	private String gender;
	private int no = 0;
	private int height, weight, AC, SBP, DBP, FBG, TC, HDL, TG, LDL, AST, ALT;
	private String cf;

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getAC() {
		return AC;
	}
	public void setAC(int aC) {
		AC = aC;
	}
	public int getSBP() {
		return SBP;
	}
	public void setSBP(int sBP) {
		SBP = sBP;
	}
	public int getDBP() {
		return DBP;
	}
	public void setDBP(int dBP) {
		DBP = dBP;
	}
	public int getFBG() {
		return FBG;
	}
	public void setFBG(int fBG) {
		FBG = fBG;
	}
	public int getTC() {
		return TC;
	}
	public void setTC(int tC) {
		TC = tC;
	}
	public int getHDL() {
		return HDL;
	}
	public void setHDL(int hDL) {
		HDL = hDL;
	}
	public int getTG() {
		return TG;
	}
	public void setTG(int tG) {
		TG = tG;
	}

	@Override
	public String toString() {
		return"[ 키=" + height + ", 몸무게=" + weight
				+ ", AC=" + AC +", SBP=" + SBP + ", DBP=" + DBP + ", FBG=" + FBG +",";
	}
	public String toString1() {
		return  "< 현재 등록값 > ";
	}
	public String toString2() {
		return  "TC=" + TC + ", HDL=" + HDL + ", TG=" + TG
				+ ", LDL=" + LDL + ", AST=" + AST + ", ALT=" + ALT + ", cf=" + cf + "]";
	}
	public int getLDL() {
		return LDL;
	}
	public void setLDL(int lDL) {
		LDL = lDL;
	}
	public int getAST() {
		return AST;
	}
	public void setAST(int aST) {
		AST = aST;
	}
	public int getALT() {
		return ALT;
	}
	public void setALT(int aLT) {
		ALT = aLT;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
}