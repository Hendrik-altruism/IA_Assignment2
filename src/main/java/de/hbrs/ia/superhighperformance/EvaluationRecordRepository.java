package de.hbrs.ia.superhighperformance;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.hbrs.ia.superhighperformance.model.EvaluationRecord;

public interface EvaluationRecordRepository extends MongoRepository<EvaluationRecord, Integer> {
    public List<EvaluationRecord> findBySid(int sid);
    public EvaluationRecord findBySidAndYear(int sid, int year);

    public boolean existsBySidAndYear(int sid, int year);

    public int deleteBySid(int sid);
    public int deleteBySidAndYear(int sid, int year);
}
