package com.example.demo.Entity;

/**
 * Created by huixiaolv on 18/05/2018.
 */
public class UserEntity {
    private String id;
    private String userName;
    private int age;
    private String address;
    private String imagesPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    @Override
    public String toString() {
        return "[id:"+id+" userName:"+userName+" age:"+age+" address:"+address+" imagesPath:"+imagesPath+"]";
    }
}
