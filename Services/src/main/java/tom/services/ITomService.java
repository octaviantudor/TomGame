package tom.services;

import tom.Jucator;
import tom.Raspuns;

import java.util.List;

public interface ITomService {

    boolean login(String username, String password, ITomObservable observable);

    void generareLitera();

    boolean verificareStart();

    Integer getJucatorId(String username,String password);

    void adaugaRaspuns(Raspuns elem);

    void notifyObservables();

    void startGame();



}
