package br.com.testpge.restaurantmanager.shared.validations.stubs;

public class StubToValidate {

    private Object id;
    private String name;
    private Integer age;
    private Boolean isAdmin;
    private Object birthDate;
    private String email;
    private StubEnum role;

    public StubToValidate(
            String name,
            Integer age,
            Boolean isAdmin,
            Object birthDate,
            String email
    ) {
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.birthDate = birthDate;
        this.email = email;

    }

    public StubToValidate(
            String name,
            Integer age,
            Boolean isAdmin,
            Object birthDate
    ) {
        this.name = name;
        this.age = age;
        this.isAdmin = isAdmin;
        this.birthDate = birthDate;
        this.email = null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StubEnum getRole() {
        return role;
    }

    public void setRole(StubEnum role) {
        this.role = role;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
