package dto;

public class healthNormalDTO {
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	private float min,max;
	
	@Override
	public String toString() {
		return "항목별 정상 수치 [항목=" + type + ", 최소값=" + min + ", 최대값=" + max + "]";
	}
	
}
