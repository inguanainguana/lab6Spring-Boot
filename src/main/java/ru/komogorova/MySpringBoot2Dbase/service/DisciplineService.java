package ru.komogorova.MySpringBoot2Dbase.service;

import ru.komogorova.MySpringBoot2Dbase.entity.Discipline;
import java.util.List;

public interface DisciplineService {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}
