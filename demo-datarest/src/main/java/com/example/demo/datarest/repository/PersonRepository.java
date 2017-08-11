package com.example.demo.datarest.repository;

import com.example.demo.datarest.entity.Person;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

/**
 * Created by liyuhong on 2017/8/10.
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    @ApiOperation("Find somebody by it's last name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query")
    })
    Person findByLastName(@Param("name") @ApiParam(name = "name") String name);
}
