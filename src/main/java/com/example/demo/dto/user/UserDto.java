package com.example.demo.dto.user;

import com.example.demo.vo.user.Hobby;
import lombok.Data;

import java.util.List;

/**
 * @Author: g6
 * @Date: 2019/4/15 11:25
 */
@Data
public class UserDto {


    public static class request{
        String userName;
        Integer gender;
        String address;
        String telPhone;
        List<Hobby> hobbies;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("userName:").append(this.userName).append("\r\n")
                    .append("gender:").append(this.gender).append("\r\n")
                    .append("address:").append(this.address).append("\r\n")
                    .append("hobbies:").append(this.userName).append("\r\n");
            return sb.toString();
        }
    }



}
