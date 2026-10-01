package ru.komogorova.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.komogorova.MySpringBoot2Dbase.dto.ResponseWrapper;
import ru.komogorova.MySpringBoot2Dbase.entity.Discipline;
import ru.komogorova.MySpringBoot2Dbase.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {
    @Autowired
    private DisciplineService disciplineService;
    @GetMapping("/disciplines")
    public ResponseEntity<ResponseWrapper<List<Discipline>>> showAllDisciplines() {
        List<Discipline> disciplines = disciplineService.getAllDisciplines();
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Список дисциплин успешно получен", disciplines));
    }
    @GetMapping("/disciplines/{id}")
    public ResponseEntity<ResponseWrapper<Discipline>> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(false, "Дисциплина с id = " + id + " не найдена", null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Дисциплина найдена", discipline));
    }

    @PostMapping("/disciplines")
    public ResponseEntity<ResponseWrapper<Discipline>> saveDiscipline(@RequestBody Discipline discipline) {
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(true, "Дисциплина успешно сохранена", savedDiscipline));
    }
    @PutMapping("/disciplines")
    public ResponseEntity<ResponseWrapper<Discipline>> updateDiscipline(@RequestBody Discipline discipline) {
        Discipline updatedDiscipline = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Дисциплина успешно обновлена", updatedDiscipline));
    }
    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(false, "Дисциплина с id = " + id + " не найдена для удаления", null));
        }
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.ok(new ResponseWrapper<>(true, "Дисциплина с id = " + id + " успешно удалена", null));
    }
}
