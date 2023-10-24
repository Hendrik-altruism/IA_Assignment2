package de.hbrs.ia.superhighperformance;

import java.util.List;

import de.hbrs.ia.superhighperformance.model.EvaluationRecord;
import de.hbrs.ia.superhighperformance.model.SalesMen;

public interface IManagePersonal {

    public void createSalesMan(SalesMen record);

    public List<SalesMen> readSalesMan();

    public SalesMen readSalesMan(int sid);

    public void updateSalesMan(SalesMen record);

    public void deleteSalesMan(int sid);

    public void createEvaluationRecord(EvaluationRecord record);

    public List<EvaluationRecord> readEvaluationRecord(int sid);

    public EvaluationRecord readEvaluationRecord(int sid, int year);

    public void updateEvaluationRecord(EvaluationRecord record);

    public void deleteEvaluationRecord(int sid, int year);

    public void deleteEvaluationRecord(int sid);
}
