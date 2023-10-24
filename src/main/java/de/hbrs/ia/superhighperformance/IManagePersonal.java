package de.hbrs.ia.superhighperformance;

import java.util.List;

import de.hbrs.ia.superhighperformance.model.SalesMen;

public interface IManagePersonal {

    public void createSalesMan(SalesMen record);

    public List<SalesMen> readSalesMan();

    public SalesMen readSalesMan(int sid);

    public void updateSalesMan(SalesMen record);

    public void deleteSalesMan(int sid);


}
