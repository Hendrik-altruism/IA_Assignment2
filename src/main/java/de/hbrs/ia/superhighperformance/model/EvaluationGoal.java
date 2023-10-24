package de.hbrs.ia.superhighperformance.model;

public enum EvaluationGoal {
	LEADERSHIP_COMPETENCE(1, "Leadership Competence"),
	OPENNESS_TO_EMPLOYEE(2, "Openness to Employee"),
	SOCIAL_BEHAVIOUR_TO_EMPLOYEE(3, "Social Behaviour to Employee"),
	ATTITUDE_TOWARDS_CLIENT(4, "Attitude towards Client"),
	COMMUNICATION_SKILLS(5, "Communication Skills"),
	INTEGRITY_TO_COMPANY(6, "Integrity to Company");
	
	private final int id;
	private final String description;

	private EvaluationGoal(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getGoalId() {
		return id;
	}

	public String getGoalDescription() {
		return description;
	}

	public static EvaluationGoal getGoalById(int id) {
		for (EvaluationGoal goal : EvaluationGoal.values()) {
			if (goal.getGoalId() == id) {
				return goal;
			}
		}
		return null;
	}
}
