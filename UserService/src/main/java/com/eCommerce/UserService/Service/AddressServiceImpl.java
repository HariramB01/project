package com.eCommerce.UserService.Service;

import com.eCommerce.UserService.Entity.Address;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Repository.AddressRepository;
import com.eCommerce.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address createAddress(Address address, Long uId) {
        User user = userRepository.findById(uId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + uId));
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Address> updateAddressById(Long id, Address address) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isPresent()) {
            Address updatedAddress = existingAddress.get();
            updatedAddress.setDoorNo(address.getDoorNo());
            updatedAddress.setStreet(address.getStreet());
            updatedAddress.setCity(address.getCity());
            updatedAddress.setState(address.getState());
            updatedAddress.setPostalCode(address.getPostalCode());

            addressRepository.save(updatedAddress);
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
