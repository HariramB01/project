package com.eCommerce.UserService.Controller;

import com.eCommerce.UserService.Entity.Address;
import com.eCommerce.UserService.Entity.User;
import com.eCommerce.UserService.Mapper.AddressMapper;
import com.eCommerce.UserService.Repository.UserRepository;
import com.eCommerce.UserService.Request.AddressReq;
import com.eCommerce.UserService.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Address createAddress(@RequestBody AddressReq addressReq) {
        System.out.println(addressReq.getuId());
        User user = userRepository.findById(addressReq.getuId()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.toString());
        Address address = AddressMapper.convertToAddress(addressReq, user);
        return addressService.createAddress(address, addressReq.getuId());
    }

    @GetMapping("/all")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddressById(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddressById(id, address);
    }
}
