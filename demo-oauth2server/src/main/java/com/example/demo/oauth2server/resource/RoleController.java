package com.example.demo.oauth2server.resource;

import com.example.demo.oauth2server.model.Role;
import com.example.demo.oauth2server.entity.RoleEntity;
import com.example.demo.oauth2server.servicer.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyuhong on 2017/8/9.
 */
@RestController
@RequestMapping("v1/Role")
public class RoleController implements CURDController<Role, Long> {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新建", notes = "新建", produces = "application/json")
    @PostMapping(value = "/")
    @Override
    public Role post(@RequestBody Role entity) {
        return roleService.create(entity);
    }

    @ApiOperation(value = "更新", notes = "更新", produces = "application/json")
    @PutMapping(value = "/")
    @Override
    public Role put(@RequestBody Role entity) {
        return roleService.put(entity);
    }

    @ApiOperation(value = "删除", notes = "删除", produces = "application/json")
    @DeleteMapping(value = "/{ID}")
    @Override
    public boolean delete(@PathVariable("ID") Long id) {
        return roleService.delete(id);
    }


    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    @GetMapping(value = "/page/{page}/size/{size}")
    @Override
    public Page<Role> getPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return roleService.getPage(page, size);
    }

}
