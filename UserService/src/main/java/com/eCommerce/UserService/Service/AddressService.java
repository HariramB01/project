package com.eCommerce.UserService.Service;

import com.eCommerce.UserService.Entity.Address;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address createAddress(Address address, Long uId);

    List<Address> getAllAddress();

    Optional<Address> getAddressById(Long id);

    void deleteAddressById(Long id);

    ResponseEntity<Address> updateAddressById(Long id, Address address);
}
