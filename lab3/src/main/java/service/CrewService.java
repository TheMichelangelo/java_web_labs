package service;

import model.Crew;

import java.util.List;

public class CrewService {
    private final static String DELETE_CREW_QUERY = "DELETE * from worker where worker_id=?";
    private final static String SELECT_CREW_QUERY = "Select * from crew where crew_id=?";
    private final static String ADD_CREW_QUERY = "DELETE * from worker where worker_id=?";
    private final static String ADD_WORKER_TO_CREW="";
    private final static String DELETE_WORKER_FROM_CREW="";

    public boolean isCrewFullComplished(Crew crew)
    {
        return false;
    }
}
