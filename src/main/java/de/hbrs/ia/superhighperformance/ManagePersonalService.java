package de.hbrs.ia.superhighperformance;

import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hbrs.ia.superhighperformance.model.SalesMen;

@Service
public class ManagePersonalService implements IManagePersonal {
	@Autowired
	private SalesMenRepository salesMenRepository;

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


}
