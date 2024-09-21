package com.eCommerce.UserService.Mapper;

import com.eCommerce.UserService.Entity.Address;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Request.AddressReq;

import java.util.Optional;

public class AddressMapper {

    public static Address convertToAddress(AddressReq addressReq, User user) {
        return new Address(
                addressReq.getDoorNo(),
                addressReq.getStreet(),
                addressReq.getCity(),
                addressReq.getState(),
                addressReq.getPostalCode(),
                user
        );
    }
}
