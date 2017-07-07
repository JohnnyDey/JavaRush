package usermanager.domain;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID", length = 8)
    @GeneratedValue
    Integer id;

    @Column(name = "NAME", length = 25)
    String name;

    @Column(name = "AGE")
    Integer age;

    @Column(name = "IS_ADMIN")
    boolean isAdmin;

    @Column(name = "CREATED_DATE")
    Timestamp cratedDate;


    public void setDate(Timestamp date) {
        this.cratedDate = date;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getDate() {

        return new Date(this.cratedDate.getTime());
    }

    public Integer getAge() {

        return age;
    }

    public String getName() {

        return name;
    }

    public Integer getId() {

        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}