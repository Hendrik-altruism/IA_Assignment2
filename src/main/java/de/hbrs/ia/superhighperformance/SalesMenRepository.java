package de.hbrs.ia.superhighperformance;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.hbrs.ia.superhighperformance.model.SalesMen;

public interface SalesMenRepository extends MongoRepository<SalesMen, Integer> {
    SalesMen findBySid(int sid);
    boolean existsBySid(int sid);
    int deleteBySid(int sid);
}
