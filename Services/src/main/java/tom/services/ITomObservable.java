package tom.services;

import tom.Jucator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITomObservable extends Remote {

    void getLetter(String litera) throws RemoteException;

    void updateTables(List<Jucator> list) throws RemoteException;
    void enableButtons() throws RemoteException;

}
