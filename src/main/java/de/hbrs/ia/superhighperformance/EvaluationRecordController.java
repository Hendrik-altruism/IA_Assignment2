package de.hbrs.ia.superhighperformance;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hbrs.ia.superhighperformance.model.EvaluationRecord;

@RestController
@RequestMapping(value = "/evaluationrecord")
public class EvaluationRecordController {

    @Autowired
    private IManagePersonal hrManager;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> createEvaluationRecord(@RequestBody EvaluationRecord record) {
        try {
            hrManager.createEvaluationRecord(record);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<String> updateEvaluationRecord(@RequestBody EvaluationRecord record) {
        try {
            hrManager.updateEvaluationRecord(record);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

	@RequestMapping(value = "/{sid}", method = RequestMethod.GET)
	public ResponseEntity<List<EvaluationRecord>> getEvaluationRecords(@PathVariable int sid) {
		return new ResponseEntity<>(hrManager.readEvaluationRecord(sid), HttpStatus.OK);
    }

    @RequestMapping(value = "/{sid}/{year}", method = RequestMethod.GET)
	public ResponseEntity<EvaluationRecord> getEvaluationRecord(@PathVariable int sid, @PathVariable int year) {
        try {
            EvaluationRecord record = hrManager.readEvaluationRecord(sid, year);

            return new ResponseEntity<EvaluationRecord>(record, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<EvaluationRecord>(HttpStatus.NOT_FOUND);
        }
	}

    @RequestMapping(value = "/{sid}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEvaluationRecords(@PathVariable int sid) {
        try {
            hrManager.deleteEvaluationRecord(sid);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{sid}/{year}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEvaluationRecord(@PathVariable int sid, @PathVariable int year) {
        try {
            hrManager.deleteEvaluationRecord(sid, year);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
