package model;

import java.util.ArrayList;
import java.util.List;

public class Crew {
    Long id;
    List<Worker> staff;

    public Crew() {
        staff = new ArrayList<Worker>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Worker> getStaff() {
        return staff;
    }

    public void setStaff(List<Worker> staff) {
        this.staff = staff;
    }
}
