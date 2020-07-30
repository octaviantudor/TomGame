package tom.repository;

import tom.Jucator;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceJucatorRepository {

    int size();
    void save(Jucator jucator) throws SQLException;
    void delete(Integer id) throws SQLException;
    void update(Jucator entity);
    Jucator findOne(String username, String password) ;
    List<Jucator> findAll();
    Jucator findOneById(Integer id);
}
