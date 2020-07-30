package tom.service;

import tom.Joc;
import tom.Jucator;
import tom.Raspuns;
import tom.repository.InterfaceJocRepository;
import tom.repository.InterfaceJucatorRepository;
import tom.repository.InterfaceRaspunsRepository;
import tom.services.ITomObservable;
import tom.services.ITomObserver;
import tom.services.ITomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TomService implements ITomService {


    private InterfaceJucatorRepository jucatorRepository;
    private InterfaceRaspunsRepository raspunsRepository;
    private InterfaceJocRepository jocRepository;
    private Map<String, ITomObservable> loggedUsers;

    private List<Raspuns> raspunsuri;
    private final int defaultNoThreads=5;

    Boolean gameRunnable = false;
    Boolean gameRunning = false;

    private Integer currentRound = 0;

    private String litera;

    Joc currentGame;

    public TomService(InterfaceJucatorRepository jucatorRepository,InterfaceRaspunsRepository raspunsRepository,InterfaceJocRepository jocRepository) {
        this.jucatorRepository = jucatorRepository;
        this.raspunsRepository=raspunsRepository;
        this.jocRepository=jocRepository;
        this.loggedUsers = new ConcurrentHashMap<>();
        raspunsuri = new ArrayList<>();
    }

    @Override
    public synchronized boolean login(String username, String password, ITomObservable observable) {
        if(loggedUsers.get(username)==null) {
            if (jucatorRepository.findOne(username, password) != null) {
                loggedUsers.put(username, observable);
                return true;
            }
        }
        return false;
    }


    @Override
    public synchronized void logout(String username) {
        loggedUsers.remove(username);
    }

    @Override
    public List<Jucator> getAll() {
        return jucatorRepository.findAll();
    }


    @Override
    public void generareLitera() {
        Random r=new Random();
        String abc="abcdefgijklmnopqrstuz";
        this.litera = String.valueOf(abc.charAt(r.nextInt(abc.length())));
    }


    @Override
    public synchronized boolean verificareStart() {
            return loggedUsers.size()==3;
    }

    @Override
    public synchronized void startGame(){

        currentRound=0;
        generareLitera();
        currentGame = new Joc(litera);
        gameRunning=true;
        notifyObservables();

    }

    @Override
    public synchronized void adaugaRaspuns(Raspuns elem){
        elem.setIdJoc(currentGame.getId());

        this.raspunsuri.add(elem);


        if(raspunsuri.size()==3){
            currentRound++;
            System.out.println(raspunsuri.get(0).getIdJucator());
            System.out.println(raspunsuri.get(1).getIdJucator());
            System.out.println(raspunsuri.get(2).getIdJucator());

            Jucator j1 = jucatorRepository.findOneById(raspunsuri.get(0).getIdJucator());
            Jucator j2 = jucatorRepository.findOneById(raspunsuri.get(1).getIdJucator());
            Jucator j3 = jucatorRepository.findOneById(raspunsuri.get(2).getIdJucator());

            j1.setPoints(j1.getPoints()+10);
            j2.setPoints(j2.getPoints()+10);
            j3.setPoints(j3.getPoints()+10);
            jucatorRepository.update(j1);
            jucatorRepository.update(j2);
            jucatorRepository.update(j3);


            for (Raspuns r:raspunsuri){
                System.out.println(r);
            }

            raspunsRepository.save(raspunsuri.get(0));
            raspunsRepository.save(raspunsuri.get(1));
            raspunsRepository.save(raspunsuri.get(2));

            raspunsuri.clear();
            notifyObservables();
        }
    }



    private void notifyAllLoggedClients(){
        ExecutorService executor= Executors.newFixedThreadPool(defaultNoThreads);
        for(ITomObservable user:loggedUsers.values()){
            executor.execute(()->{
                System.out.println("Notifying client...");
                try{
                    if(currentRound!=3) {
                        user.updateTables(jucatorRepository.findAll());
                        user.enableButtons();
                        user.getLetter(litera);
                    }
                    else{
                        user.updateTables(jucatorRepository.findAll());

                    }

                }catch (Exception e){
                    System.out.println("error notifying client...");
                }
            });
        }
        executor.shutdown();
    }

    @Override
    public Integer getJucatorId(String username,String password){
        Jucator jucator = jucatorRepository.findOne(username,password);
        if (jucator != null){
            return jucator.getId();
        }
        return null;
    }

    @Override
    public void notifyObservables() {
        notifyAllLoggedClients();
    }




}
