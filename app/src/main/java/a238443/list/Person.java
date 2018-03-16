package a238443.list;


public class Person {
    private String s_name;
    private String s_surname;
    private int i_age;

    Person(String sName, String sSurname, int iAge) {
        s_name=sName;
        s_surname=sSurname;
        i_age=iAge;
    }


    public void v_setAge(int iAge) {
        this.i_age = iAge;
    }

    public void v_setSurname(String sSurname) {
        this.s_surname = sSurname;
    }

    public void v_setName(String sName) {
        this.s_name = sName;
    }

    public int i_getAge() {

        return i_age;
    }

    public String s_getSurname() {

        return s_surname;
    }

    public String s_getName() {

        return s_name;
    }
}
