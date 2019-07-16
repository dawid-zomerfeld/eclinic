package pl.eclinic.repository;

import org.springframework.stereotype.Repository;
import pl.eclinic.domain.Patient;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PatientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Patient patient) {
        entityManager.persist(patient);

    }

    public Patient get(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        return patient;
    }

    public void update(Patient patient) {
        Patient find = entityManager.find(Patient.class, patient.getIdPatient());
        if(find != null) {
            find.setFirstName(patient.getFirstName());
            find.setLastName(patient.getLastName());
        }
    }

    public void remove(Long patientId) {
        Patient objToRemove = entityManager.find(Patient.class, patientId);
        entityManager.remove(objToRemove);
        System.out.println(objToRemove.getIdPatient());
    }
}
