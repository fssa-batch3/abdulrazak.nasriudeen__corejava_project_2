package model;

public abstract class UserEntities  {
    private int id;
    private String name;
    private long number;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }


    public long getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "UserEntities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", password='" + password + '\'' +
                '}';
    }
}


