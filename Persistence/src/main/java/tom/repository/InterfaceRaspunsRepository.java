package tom.repository;

import tom.Raspuns;

import java.util.List;

public interface InterfaceRaspunsRepository {

    List<Raspuns> getAll();
    void save(Raspuns raspuns);

}
