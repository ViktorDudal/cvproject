package com.gmail.viktordudal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gmail.viktordudal.dao.PersonDao;
import com.gmail.viktordudal.model.Company;
import com.gmail.viktordudal.model.Contact;
import com.gmail.viktordudal.model.Person;
import com.gmail.viktordudal.model.Specialization;

public class PersonService {

    private PersonDao personDao = new PersonDao();

    public List<Person> getAll() {
        return personDao.getAll();
    }

//    public List<Person> getAll() {
//        List<Person> people = new ArrayList<>();
//        people.add(getMockPerson(1,"Viktor", "Dudal", LocalDate.of(1984, 10, 3), Specialization.JAVA));
//        people.add(getMockPerson(2,"Ivan", "Ivanov", LocalDate.of(1983,10,5), Specialization.DEV_OPS));
//        people.add(getMockPerson(3,"Petr", "Petrov", LocalDate.of(1985, 1, 18), Specialization.PYTHON));
//        people.add(getMockPerson(4,"Roman", "Kocherga", LocalDate.of(1982, 3, 21), Specialization.RUBY));
//        people.add(getMockPerson(5,"Fedor", "Fedorov", LocalDate.of(1981, 5, 23), Specialization.WEB_UI));
//        people.add(getMockPerson(6,"Oleg", "Polunin", LocalDate.of(1986, 7, 29), Specialization.RUBY));
//        people.add(getMockPerson(7,"Sergey", "Sidorov", LocalDate.of(1989, 11, 8), Specialization.JAVA));
//        people.add(getMockPerson(8,"Vadim", "Romanenko", LocalDate.of(1991, 8, 9), Specialization.PYTHON));
//        people.add(getMockPerson(9,"Svetlana", "Skvorcova", LocalDate.of(1987, 9, 24), Specialization.WEB_UI));
//        people.add(getMockPerson(10,"Marina", "Radina", LocalDate.of(1983, 7, 1), Specialization.JAVA));
//        people.add(getMockPerson(11,"Olena", "Lugova", LocalDate.of(1981, 2, 27), Specialization.JAVA));
//        people.add(getMockPerson(12,"Vladimir", "Logvin", LocalDate.of(1984, 5, 18), Specialization.JAVA));
//        return people;
//    }
//
//    public List<Person> getBySpec(String spId) {
//        List<Person> people = new ArrayList<>();
//        if (spId.equals(Specialization.JAVA.getName())){
//            people.add(getMockPerson(1,"Viktor", "Dudal", LocalDate.of(1984, 10, 3), Specialization.JAVA));
//            people.add(getMockPerson(7,"Sergey", "Sidorov", LocalDate.of(1989, 11, 8), Specialization.JAVA));
//        }
//        if (spId.equals(Specialization.PYTHON.getName())){
//            people.add(getMockPerson(3,"Petr", "Petrov", LocalDate.of(1985, 1, 18), Specialization.PYTHON));
//            people.add(getMockPerson(8,"Vadim", "Romanenko", LocalDate.of(1991, 8, 9), Specialization.PYTHON));
//        }
//        if (spId.equals(Specialization.DEV_OPS.getName())){
//            people.add(getMockPerson(2,"Ivan", "Ivanov", LocalDate.of(1983,10,5), Specialization.DEV_OPS));
//        }
//        if (spId.equals(Specialization.WEB_UI.getName())){
//            people.add(getMockPerson(5,"Fedor", "Fedorov", LocalDate.of(1981, 5, 23), Specialization.WEB_UI));
//            people.add(getMockPerson(9,"Svetlana", "Skvorcova", LocalDate.of(1987, 9, 24), Specialization.WEB_UI));
//        }
//        if (spId.equals(Specialization.RUBY.getName())){
//            people.add(getMockPerson(4,"Roman", "Kocherga", LocalDate.of(1982, 3, 21), Specialization.RUBY));
//            people.add(getMockPerson(6,"Oleg", "Polunin", LocalDate.of(1986, 7, 29), Specialization.RUBY));
//        }
//        return people;
//    }
//
//    public Person getById(int id) {
//        if (id == 1){
//            return getMockPerson(1,"Viktor", "Dudal", LocalDate.of(1984, 10, 3), Specialization.JAVA);
//        }
//        if (id == 2){
//            return getMockPerson(2,"Ivan", "Ivanov", LocalDate.of(1983,10,5), Specialization.DEV_OPS);
//        }
//        if (id == 3){
//            return getMockPerson(3,"Petr", "Petrov", LocalDate.of(1985, 1, 18), Specialization.PYTHON);
//        }
//        return null;
//    }

//    public Person getById(long id) {
//        return personDao.getById(id);
//    }

//    private Person getMockPerson(long id, String name, String surname, LocalDate dateOfBirth, Specialization spec){
//
//        return Person.builder()
//                .id(id)
//                .surname(surname)
//                .name(name)
//                .dateOfBirth(LocalDate.of(1984, 10, 03))
//                .contact(Contact.builder()
//                        .city("Rivne")
//                        .address("Street")
//                        .phoneNumber("0660142900")
//                        .email("vit@fdfg.com")
//                        .build())
//                .company(Company.builder()
//                        .companyName("SoftServe")
//                        .position("Junior Developer")
//                        .workedFrom(LocalDate.of(2008,10,15))
//                        .workedTill(LocalDate.of(2012,1,7))
//                        .build())
//                .skills("HTML")
//                .skills("CSS")
//                .specialization(spec.getName())
//                .build();
//    }
}