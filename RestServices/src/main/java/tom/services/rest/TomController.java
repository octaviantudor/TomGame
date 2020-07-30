package tom.services.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tom.Jucator;
import tom.repository.InterfaceJucatorRepository;
import tom.repository.RepositoryException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/tom/jucatori")
public class TomController {

    private static final String template = "Hello, %s!";

    @Autowired
    private InterfaceJucatorRepository jucatorRepository;


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @RequestMapping( method= RequestMethod.GET)
    public List<Jucator> getAll() throws SQLException {
        return jucatorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Jucator create(@RequestBody Jucator jucator) throws SQLException {
        jucatorRepository.save(jucator);
        return jucator;

    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String jucatorError(RepositoryException e) {
        return e.getMessage();
    }
}
