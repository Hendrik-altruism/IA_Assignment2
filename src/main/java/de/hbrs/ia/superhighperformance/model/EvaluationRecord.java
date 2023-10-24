package de.hbrs.ia.superhighperformance.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "evaluationRecords")
public class EvaluationRecord {
	private int sid;
	private int year;
	private List<EvaluationCriterion> criterions;

	EvaluationRecord() {}

    public EvaluationRecord(int sid, int year) {
		this.sid = sid;
		this.year = year;
        criterions = new ArrayList<EvaluationCriterion>();
	}

	@PersistenceConstructor
	public EvaluationRecord(int sid, int year, List<EvaluationCriterion> criterions) {
		this.sid = sid;
		this.year = year;
        this.criterions = criterions;
    }

	public void addCriterion(EvaluationCriterion criterion) {
		criterions.add(criterion);
	}

	public void setCriterions(List<EvaluationCriterion> criterions) {
		this.criterions = criterions;
	}

	public List<EvaluationCriterion> getCriterions() {
		return criterions;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof EvaluationRecord)) return false;
		EvaluationRecord evaluationRecord = (EvaluationRecord) obj;
		if (this.getSid() != evaluationRecord.getSid()) return false;
		if (this.getYear() != evaluationRecord.getYear()) return false;
		if (this.getCriterions().size() != evaluationRecord.getCriterions().size()) return false;
		for (EvaluationCriterion criterion : this.getCriterions()) {
			if (!(evaluationRecord.getCriterions().contains(criterion))) return false;
		}
		return true;
	}

	@Id
	public int getId() {
		return sid + year;
	}
}
