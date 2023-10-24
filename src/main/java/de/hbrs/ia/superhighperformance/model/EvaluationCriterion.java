package de.hbrs.ia.superhighperformance.model;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EvaluationCriterion {
	private int goalId;
	private int targetValue;
	private int actualValue;

	EvaluationCriterion() {}

	public EvaluationCriterion(EvaluationGoal goal, int targetValue, int actualValue) {
		this.goalId = goal.getGoalId();
		this.targetValue = targetValue;
		this.actualValue = actualValue;
	}

	@PersistenceConstructor
	public EvaluationCriterion(int goalId, int targetValue, int actualValue) {
		this.goalId = goalId;
		this.targetValue = targetValue;
		this.actualValue = actualValue;
	}

	public int getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(int targetValue) {
		this.targetValue = targetValue;
	}

	public int getActualValue() {
		return actualValue;
	}

	public void setActualValue(int actualValue) {
		this.actualValue = actualValue;
	}

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getGoalDescription() {
		return EvaluationGoal.getGoalById(this.goalId).getGoalDescription();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof EvaluationCriterion)) return false;
		EvaluationCriterion criterion = (EvaluationCriterion) obj;
		return (this.getGoalId() == criterion.getGoalId()
				&& this.getTargetValue() == criterion.getTargetValue()
				&& this.getActualValue() == criterion.getActualValue());
	}
}
