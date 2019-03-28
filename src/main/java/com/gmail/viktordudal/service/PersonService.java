package com.gmail.viktordudal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

public class PersonService {

    private PersonDao personDao = new PersonDao();

    private static long counter = 1;

    private static List<Person> persons = new ArrayList<>();

    static {
        init();
    }

    public PersonService(){
    }

    private static void init() {
        persons.add(getMockPerson("Viktor", "Dudal", LocalDate.of(1984, 10, 3), Specialization.JAVA));
        persons.add(getMockPerson("Ivan", "Ivanov", LocalDate.of(1983,10,5), Specialization.DEV_OPS));
        persons.add(getMockPerson("Petr", "Petrov", LocalDate.of(1985, 1, 18), Specialization.PYTHON));
        persons.add(getMockPerson("Roman", "Kocherga", LocalDate.of(1982, 3, 21), Specialization.RUBY));
        persons.add(getMockPerson("Fedor", "Fedorov", LocalDate.of(1981, 5, 23), Specialization.WEB_UI));
        persons.add(getMockPerson("Oleg", "Polunin", LocalDate.of(1986, 7, 29), Specialization.RUBY));
        persons.add(getMockPerson("Sergey", "Sidorov", LocalDate.of(1989, 11, 8), Specialization.JAVA));
        persons.add(getMockPerson("Vadim", "Romanenko", LocalDate.of(1991, 8, 9), Specialization.PYTHON));
        persons.add(getMockPerson("Svetlana", "Skvorcova", LocalDate.of(1987, 9, 24), Specialization.WEB_UI));
        persons.add(getMockPerson("Marina", "Radina", LocalDate.of(1983, 7, 1), Specialization.JAVA));
        persons.add(getMockPerson("Olena", "Lugova", LocalDate.of(1981, 2, 27), Specialization.JAVA));
        persons.add(getMockPerson("Vladimir", "Logvin", LocalDate.of(1984, 5, 18), Specialization.JAVA));
    }

//    public List<Person> getAll() {
//        return personDao.getAll();
//    }

    public List<Person> getAll() {
        return persons;
    }

    public List<Person> getBySpec(Specialization specialization) {
        return persons.stream()
                .filter(person -> specialization.equals(person.getSpecialization()))
                .collect(Collectors.toList());
    }

    public Person getById(long id) {
        return persons.stream()
                .filter(person -> id == person.getId())
                .findFirst().orElse(null);
    }

//    public Person getById(long id) {
//        return personDao.getById(id);
//    }

    private static Person getMockPerson(String name, String surname, LocalDate dateOfBirth, Specialization spec){

        return Person.builder()
                .id(counter++)
                .surname(surname)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .contact(Contact.builder()
                        .city("Rivne")
                        .address("Street")
                        .phoneNumber("0660142900")
                        .email("vit@fdfg.com")
                        .build())
                .company(Company.builder()
                        .companyName("SoftServe")
                        .position("Junior Developer")
                        .workedFrom(LocalDate.of(2008,10,15))
                        .workedTill(LocalDate.of(2012,1,7))
                        .build())
                .company(Company.builder()
                        .companyName("ITE NAS & MES")
                        .position("Engineer")
                        .workedFrom(LocalDate.of(2008,10,15))
                        .workedTill(LocalDate.of(2012,1,7))
                        .build())
                .skill("HTML")
                .skill("CSS")
                .skill("JS")
                .specialization(spec)
                .build();
    }

    public void addNewPerson(Person newPerson) {
        newPerson.setId(counter++);
        persons.add(newPerson);
    }

    public boolean deleteById(long id) {
        Person personToDelete = persons.stream().filter(person -> id == person.getId()).findFirst().orElse(null);
        return persons.remove(personToDelete);
    }

    public void updatePerson(Person newPerson) {
        int idToUpdate = 0;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() == newPerson.getId()){
                idToUpdate = i;
            }
        }
        persons.remove(persons.get(idToUpdate));
        persons.add(idToUpdate, newPerson);

    }

    public boolean addPersonFromFile(String absolutePath) {
        //use parser to parse file and add to DB
        return true;
    }
}