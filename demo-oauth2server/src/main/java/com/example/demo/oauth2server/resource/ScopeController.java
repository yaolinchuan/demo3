package com.example.demo.oauth2server.resource;

import com.example.demo.oauth2server.entity.ScopeEntity;
import com.example.demo.oauth2server.model.Scope;
import com.example.demo.oauth2server.servicer.ScopeService;
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
@RequestMapping("v1/Scope")
public class ScopeController implements CURDController<Scope, Long> {

    @Autowired
    private ScopeService scopeService;

    @ApiOperation(value = "新建", notes = "新建", produces = "application/json")
    @PostMapping(value = "/")
    @Override
    public Scope post(@RequestBody Scope entity) {
        return scopeService.create(entity);
    }


    @ApiOperation(value = "更新", notes = "更新", produces = "application/json")
    @PutMapping(value = "/")
    @Override
    public Scope put(@RequestBody Scope entity) {
        return scopeService.put(entity);
    }

    @ApiOperation(value = "删除", notes = "删除", produces = "application/json")
    @DeleteMapping(value = "/{ID}")
    @Override
    public boolean delete(@PathVariable("ID") Long id) {
        return scopeService.delete(id);
    }


    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    @GetMapping(value = "/page/{page}/size/{size}")
    @Override
    public Page<Scope> getPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return scopeService.getPage(page, size);
    }
}
