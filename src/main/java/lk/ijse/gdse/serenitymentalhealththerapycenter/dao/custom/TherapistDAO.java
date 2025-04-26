package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;

public interface TherapistDAO extends CrudDAO<Therapist, String> {

    String getNextID();
    ArrayList<String> getTherapist();
}
