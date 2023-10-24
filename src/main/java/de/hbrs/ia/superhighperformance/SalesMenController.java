package de.hbrs.ia.superhighperformance;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hbrs.ia.superhighperformance.model.SalesMen;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping(value = "/salesmen")
public class SalesMenController {

	@Autowired
	private IManagePersonal hrManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<SalesMen>> getSalesMen() {
		return new ResponseEntity<>(hrManager.readSalesMan(), HttpStatus.OK);
	}


	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> setSalesMen(@RequestBody SalesMen salesmen) {
		if (isSalesMenInvalid(salesmen)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				hrManager.createSalesMan(salesmen);
				// Create a new HttpHeader and insert the URI just created for that salesman
				URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path( String.valueOf( salesmen.getSid() ))
						.buildAndExpand()
						.toUri();

				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(location);
				return new ResponseEntity<>( headers , HttpStatus.CREATED);

			} catch (InvalidInputException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	private boolean isSalesMenInvalid(SalesMen salesmen) {
		return salesmen == null
				|| salesmen.getFirstName() == null || salesmen.getFirstName().isEmpty()
				|| salesmen.getLastName() == null || salesmen.getLastName().isEmpty();
	}
}
