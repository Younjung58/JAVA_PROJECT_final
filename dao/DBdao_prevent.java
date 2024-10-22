package dao;

import dto.preventDiseaseDTO;

public interface DBdao_prevent {
	public preventDiseaseDTO selectOne(String type);
}
