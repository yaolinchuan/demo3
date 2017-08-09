package com.example.demo.oauth2server.resource;

import com.example.demo.oauth2server.entity.AuthorityEntity;

import com.example.demo.oauth2server.model.Authority;
import com.example.demo.oauth2server.servicer.AuthorityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
@RestController
@RequestMapping("v1/Authority")
public class AuthorityController implements CURDController<Authority, Long> {

    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "新建", notes = "新建", produces = "application/json")
    @PostMapping(value = "/")
    @Override
    public Authority post(@RequestBody Authority entity) {

        return authorityService.create(entity);
    }

    @ApiOperation(value = "更新", notes = "更新", produces = "application/json")
    @PutMapping(value = "/")
    @Override
    public Authority put(@RequestBody Authority entity) {
        return authorityService.put(entity);
    }

    @ApiOperation(value = "删除", notes = "删除", produces = "application/json")
    @DeleteMapping(value = "/{ID}")
    @Override
    public boolean delete(@PathVariable("ID") Long id) {
        return authorityService.delete(id);
    }


    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    @GetMapping(value = "/page/{page}/size/{size}")
    @Override
    public Page<Authority> getPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return authorityService.getPage(page, size);
    }
}
