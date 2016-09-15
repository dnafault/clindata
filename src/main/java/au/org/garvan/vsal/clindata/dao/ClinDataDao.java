package au.org.garvan.vsal.clindata.dao;

import java.util.List;

import au.org.garvan.vsal.clindata.entities.ClinData;

public interface ClinDataDao {

	/**
	 * Returns all ClinData
	 * @return
	 */
	List<ClinData> getClinData();

	/**
	 * Returns ClinData for a given id
	 * 
	 * @param id
	 * @return
	 */
	ClinData getClinDataById(String id);

	List<String> findIDs(String gender,
						 Integer yobStart, Integer yobEnd,
						 Integer sbpStart, Integer sbpEnd,
						 Float heightStart, Float heightEnd,
						 Float weightStart, Float weightEnd,
						 Integer abdCircStart, Integer abdCircEnd,
						 Float glcStart, Float glcEnd);
}
