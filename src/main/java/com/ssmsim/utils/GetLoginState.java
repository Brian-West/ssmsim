package com.ssmsim.utils;

public class GetLoginState {

    public static String checkPassword(String right, String tobeChecked) {
        if(right != null) {
            if(right.equals(tobeChecked)) {
                //student.setState("success");
                return "success";
            } else {
                //student.setState("wrongPwd");
                return "wrongPwd";
            }
        } else {
            //student = new Student();
            //student.setState("notExist");
            return "notExist";
        }
    }
}
