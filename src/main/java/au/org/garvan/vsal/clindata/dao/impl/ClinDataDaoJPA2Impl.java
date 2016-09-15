package au.org.garvan.vsal.clindata.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import au.org.garvan.vsal.clindata.dao.ClinDataDao;
import au.org.garvan.vsal.clindata.entities.ClinData;

public class ClinDataDaoJPA2Impl implements ClinDataDao {

	@PersistenceContext(unitName="clinDataPersistence")
	private EntityManager entityManager;
	
	public List<ClinData> getClinData() {
		String qlString = "SELECT p FROM ClinData  p";
		TypedQuery<ClinData> query = entityManager.createQuery(qlString, ClinData.class);
		return query.getResultList();
	}

	public ClinData getClinDataById(String id) {
		try {
			String qlString = "SELECT p FROM ClinData  p WHERE p.id = ?1";
			TypedQuery<ClinData> query = entityManager.createQuery(qlString, ClinData.class);
			query.setParameter(1, id);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<String> findIDs(String gender,
                                Integer yobStart, Integer yobEnd,
                                Integer sbpStart, Integer sbpEnd,
                                Float heightStart, Float heightEnd,
                                Float weightStart, Float weightEnd,
                                Integer abdCircStart, Integer abdCircEnd,
                                Float glcStart, Float glcEnd) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClinData> cq = cb.createQuery(ClinData.class);
        Root<ClinData> root = cq.from(ClinData.class);

        Predicate predGender = cb.and();
        Predicate predYOB = cb.and();
        Predicate predSBP = cb.and();
        Predicate predHeight = cb.and();
        Predicate predWeight = cb.and();
        Predicate predAbdC = cb.and();
        Predicate predGlc = cb.and();

        ParameterExpression<String>  paramGender=null;

        if (gender != null && !gender.isEmpty()) {
            paramGender = cb.parameter(String.class);
            predGender = cb.equal(root.get("gender"), paramGender);
        }

        if (yobStart != null && yobEnd != null) {
            Expression<Integer> yob = root.get("yob");
            predYOB = cb.between(yob, yobStart, yobEnd);
        }

        if (sbpStart != null && sbpEnd != null) {
            Expression<Integer> sbp = root.get("sbp");
            predSBP = cb.between(sbp, sbpStart, sbpEnd);
        }

        if (heightStart != null && heightEnd != null) {
            Expression<Float> height = root.get("height");
            predHeight = cb.between(height, heightStart, heightEnd);
        }

        if (weightStart != null && weightEnd != null) {
            Expression<Float> weight = root.get("weight");
            predWeight = cb.between(weight, weightStart, weightEnd);
        }

        if (abdCircStart != null && abdCircEnd != null) {
            Expression<Integer> abd = root.get("abdoCirc");
            predAbdC = cb.between(abd, abdCircStart, abdCircEnd);
        }

        if (glcStart != null && glcEnd != null) {
            Expression<Float> glc = root.get("glc");
            predGlc = cb.between(glc, glcStart, glcEnd);
        }

        TypedQuery<ClinData> qFinal = entityManager.createQuery(cq.select(root)
                .where(cb.and(predGender,predYOB,predSBP,predHeight,predWeight,predAbdC,predGlc)));

        if (paramGender != null) {
            qFinal.setParameter(paramGender, gender);
        }

        List<ClinData> allitems = qFinal.getResultList();
        List<String> result = new ArrayList<String>();

        for(ClinData cd : allitems) {
            if (cd.getSampleId() != null && !cd.getSampleId().isEmpty()) {
                result.add(cd.getSampleId());
            }
        }

        return result;
	}

}