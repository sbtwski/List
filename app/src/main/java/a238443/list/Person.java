package a238443.list;


import java.io.Serializable;

class Person implements Serializable{
    private String s_name;
    private String s_surname;
    private int i_age;

    Person(String sName, String sSurname, int iAge) {
        s_name=sName;
        s_surname=sSurname;
        i_age=iAge;
    }

    int i_getAge() {

        return i_age;
    }

    String s_getSurname() {

        return s_surname;
    }

    String s_getName() {

        return s_name;
    }
}
