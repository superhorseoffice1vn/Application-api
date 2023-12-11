package com.example.be.validators;

import com.example.be.dto.request.SignUpForm;
import com.example.be.dto.response.ResponseMessage;

public class UserValidator {

    public static ResponseMessage validatorUser(
            SignUpForm addUserRequest
    ){
        ResponseMessage message;

        message = validateName(addUserRequest.getName());
        if (message != null) {
            return message;
        }

        message = validatePhoneNumber(addUserRequest.getPhoneNumber());
        if (message != null) {
            return message;
        }

        return new ResponseMessage("");
    }

    private static ResponseMessage validateName(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            return createCommonMessage("Name cannot be empty.");
        }

        if (name.length() < 5) {
            return createCommonMessage("Name must be at least 5 characters long.");
        }

        if (name.length() > 50) {
            return createCommonMessage("Name cannot exceed 50 characters.");
        }
        return null;
    }

    private static ResponseMessage validatePhoneNumber(String phone){
        phone = phone.trim();
        if (phone.isEmpty()){
            return createCommonMessage("Phone cannot be empty.");
        }
        return null;
    }

    private static ResponseMessage validateBirthDay(String birthDay){
        birthDay = birthDay.trim();
        if (birthDay.isEmpty()){
            return createCommonMessage("Birth day cannot be empty.");
        }
        return null;
    }

    private static ResponseMessage validateAddress(String address){
        address = address.trim();
        if (address.isEmpty()){
            return createCommonMessage("Address cannot be empty.");
        }
        if (address.length() > 100){
            return createCommonMessage("Name cannot exceed 100 characters.");
        }
        return null;
    }

    private static ResponseMessage validateIdCard(String idCard){
        idCard = idCard.trim();
        if (idCard.isEmpty()){
            return createCommonMessage("IdCard cannot be empty.");
        }
        return null;
    }

    private static ResponseMessage validateGender(String gender){
        gender = gender.trim();
        if (gender.isEmpty()){
            return createCommonMessage("Gender day cannot be empty.");
        }
        return null;
    }

    private static ResponseMessage validateIdUserType(Integer idUserType){
        if (idUserType == null){
            return createCommonMessage("IdUserType cannot be null.");
        }
        return null;
    }

    private static ResponseMessage createCommonMessage(String params) {
        return new ResponseMessage( params);
    }
}
