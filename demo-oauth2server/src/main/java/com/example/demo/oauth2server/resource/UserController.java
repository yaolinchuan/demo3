package com.example.demo.oauth2server.resource;

import com.example.demo.oauth2server.entity.UserEntity;
import com.example.demo.oauth2server.model.User;
import com.example.demo.oauth2server.repository.UserRepository;
import com.example.demo.oauth2server.servicer.DatabaseUserDetailService;
import com.example.demo.oauth2server.servicer.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
@RestController
@RequestMapping("v1/user")
public class UserController implements CURDController<User, Long> {
    @Autowired
    private UserService userService;
    @Autowired
    private DatabaseUserDetailService databaseUserDetailService;

    @ApiOperation(value = "新建", notes = "新建", produces = "application/json")
    @PostMapping(value = "/")
    @Override
    public User post(@RequestBody User Entity) {
        Entity = userService.create(Entity);
        return Entity;
    }

    @ApiOperation(value = "更新", notes = "更新", produces = "application/json")
    @PutMapping(value = "/")
    @Override
    public User put(@RequestBody User userEntity) {
        userEntity = userService.put(userEntity);
        return userEntity;
    }


    @ApiOperation(value = "删除", notes = "删除", produces = "application/json")
    @DeleteMapping(value = "/{ID}")
    @Override
    public boolean delete(@PathVariable("ID") Long id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/page/{page}/size/{size}")
    @Override
    public Page<User> getPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return userService.getPage(page, size);
    }

    @ApiOperation(value = "username查询", notes = "username查询", produces = "application/json")
    @GetMapping(value = "/Username/{username}")
    public UserDetails loadUserByUsername(@PathVariable("username") String username) {
        return databaseUserDetailService.loadUserByUsername(username);
    }
}
