package Logic;

public abstract class Person {
    protected int Access;
    protected String Name;

    public Person(int access, String name) {
        Access = access;
        Name = name;
    }

    public int getAccess() {
        return Access;
    }
    public void setAccess(int access) {
        Access = access;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
