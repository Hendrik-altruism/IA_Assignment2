package de.hbrs.ia.superhighperformance;

import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hbrs.ia.superhighperformance.model.EvaluationCriterion;
import de.hbrs.ia.superhighperformance.model.EvaluationGoal;
import de.hbrs.ia.superhighperformance.model.EvaluationRecord;
import de.hbrs.ia.superhighperformance.model.SalesMen;

@Service
public class ManagePersonalService implements IManagePersonal {
	@Autowired
	private SalesMenRepository salesMenRepository;

	@Autowired
	private EvaluationRecordRepository evaluationRecordRepository;

	@PostConstruct
	private void fillWithExampleData() {
		// create salesmen test data
		salesMenRepository.deleteAll();

		try {
			this.createSalesMan(new SalesMen(91782, "William", "Riden"));
			this.createSalesMan(new SalesMen(84234, "Mary-Ann", "Sallinger"));
			this.createSalesMan(new SalesMen(90123, "John", "Smith"));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		// create evaluation records test data
		evaluationRecordRepository.deleteAll();

		EvaluationRecord evaRecRiden = new EvaluationRecord(91782, 2019);
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.LEADERSHIP_COMPETENCE, 4, 3));
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.OPENNESS_TO_EMPLOYEE, 4, 4));
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.SOCIAL_BEHAVIOUR_TO_EMPLOYEE, 4, 5));
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.ATTITUDE_TOWARDS_CLIENT, 4, 3));
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.COMMUNICATION_SKILLS, 4, 3));
		evaRecRiden.addCriterion(new EvaluationCriterion(EvaluationGoal.INTEGRITY_TO_COMPANY, 4, 4));

		EvaluationRecord evaRecRidenOld = new EvaluationRecord(91782, 2018);
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.LEADERSHIP_COMPETENCE, 4, 3));
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.OPENNESS_TO_EMPLOYEE, 4, 3));
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.SOCIAL_BEHAVIOUR_TO_EMPLOYEE, 4, 4));
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.ATTITUDE_TOWARDS_CLIENT, 4, 3));
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.COMMUNICATION_SKILLS, 4, 2));
		evaRecRidenOld.addCriterion(new EvaluationCriterion(EvaluationGoal.INTEGRITY_TO_COMPANY, 4, 4));

		EvaluationRecord evaRecSallinger = new EvaluationRecord(84234, 2019);
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.LEADERSHIP_COMPETENCE, 4, 4));
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.OPENNESS_TO_EMPLOYEE, 4, 3));
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.SOCIAL_BEHAVIOUR_TO_EMPLOYEE, 4, 4));
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.ATTITUDE_TOWARDS_CLIENT, 4, 4));
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.COMMUNICATION_SKILLS, 4, 5));
		evaRecSallinger.addCriterion(new EvaluationCriterion(EvaluationGoal.INTEGRITY_TO_COMPANY, 4, 5));

		EvaluationRecord evaRecSmith = new EvaluationRecord(90123, 2019);
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.LEADERSHIP_COMPETENCE, 4, 5));
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.OPENNESS_TO_EMPLOYEE, 4, 3));
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.SOCIAL_BEHAVIOUR_TO_EMPLOYEE, 4, 4));
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.ATTITUDE_TOWARDS_CLIENT, 4, 2));
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.COMMUNICATION_SKILLS, 4, 4));
		evaRecSmith.addCriterion(new EvaluationCriterion(EvaluationGoal.INTEGRITY_TO_COMPANY, 4, 3));

		try {
			this.createEvaluationRecord(evaRecRiden);
			this.createEvaluationRecord(evaRecRidenOld);
			this.createEvaluationRecord(evaRecSallinger);
			this.createEvaluationRecord(evaRecSmith);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createSalesMan(SalesMen record) throws InvalidInputException {
		if (record == null || salesMenRepository.existsBySid(record.getSid())) {
			throw new InvalidInputException();
		} else {
			salesMenRepository.save(record);
		}
	}

	@Override
	public List<SalesMen> readSalesMan() {
		return salesMenRepository.findAll();
	}

	@Override
	public SalesMen readSalesMan(int sid) throws NoSuchElementException {
		SalesMen salesMen = salesMenRepository.findBySid(sid);

		if (salesMen == null) {
			throw new NoSuchElementException();
		} else {
			return salesMen;
		}
	}

	@Override
	public void updateSalesMan(SalesMen record) throws InvalidInputException {
		if (record == null || !salesMenRepository.existsBySid(record.getSid())) {
			throw new InvalidInputException();
		}
		else {
			salesMenRepository.save(record);
		}
	}

	@Override
	public void deleteSalesMan(int sid) throws NoSuchElementException {
		if (salesMenRepository.deleteBySid(sid) == 0) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void createEvaluationRecord(EvaluationRecord record) throws InvalidInputException {
		if (record == null || evaluationRecordRepository.existsBySidAndYear(record.getSid(), record.getYear())) {
			throw new InvalidInputException();
		}
		else {
			evaluationRecordRepository.save(record);
		}
	}

	@Override
	public List<EvaluationRecord> readEvaluationRecord(int sid) {
		return evaluationRecordRepository.findBySid(sid);
	}

	@Override
	public EvaluationRecord readEvaluationRecord(int sid, int year) throws NoSuchElementException {
		EvaluationRecord record = evaluationRecordRepository.findBySidAndYear(sid, year);

		if (record == null) {
			throw new NoSuchElementException();
		}
		else {
			return record;
		}
	}

	@Override
	public void updateEvaluationRecord(EvaluationRecord record) throws InvalidInputException {
		if (record == null || evaluationRecordRepository.existsBySidAndYear(record.getSid(), record.getYear())) {
			throw new InvalidInputException();
		}
		else {
			evaluationRecordRepository.save(record);
		}
	}

	@Override
	public void deleteEvaluationRecord(int sid, int year) throws NoSuchElementException {
		if (evaluationRecordRepository.deleteBySidAndYear(sid, year) == 0) {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void deleteEvaluationRecord(int sid) throws NoSuchElementException {
		if (evaluationRecordRepository.deleteBySid(sid) == 0) {
			throw new NoSuchElementException();
		}
	}
}
