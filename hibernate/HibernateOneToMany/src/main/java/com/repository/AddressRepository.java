package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
