package dto;

public class preventDiseaseDTO {
	private String type, state, memo;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return ("나의 예방단계 : " + type + " \n상태 설명 : " + state + "\n최종 코멘트 : \n" + memo );
	}
}
