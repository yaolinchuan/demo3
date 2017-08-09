package com.example.demo.oauth2server.resource;

import com.example.demo.oauth2server.entity.ResourceIdEntity;
import com.example.demo.oauth2server.model.ResourceId;
import com.example.demo.oauth2server.servicer.ResourceIdService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
@RestController
@RequestMapping("v1/ResourceId")
public class ResourceIdController implements CURDController<ResourceId, Long> {

    @Autowired
    private ResourceIdService resourceIdService;

    @ApiOperation(value = "新建", notes = "新建", produces = "application/json")
    @PostMapping(value = "/")
    @Override
    public ResourceId post(@RequestBody ResourceId entity) {
        return resourceIdService.create(entity);
    }

    @ApiOperation(value = "更新", notes = "更新", produces = "application/json")
    @PutMapping(value = "/")
    @Override
    public ResourceId put(@RequestBody ResourceId entity) {
        return resourceIdService.put(entity);
    }

    @ApiOperation(value = "删除", notes = "删除", produces = "application/json")
    @DeleteMapping(value = "/{ID}")
    @Override
    public boolean delete(@PathVariable("ID") Long id) {
        return resourceIdService.delete(id);
    }


    @ApiOperation(value = "分页查询", notes = "分页查询", produces = "application/json")
    @GetMapping(value = "/page/{page}/size/{size}")
    @Override
    public Page<ResourceId> getPage(@PathVariable("page") int page, @PathVariable(value = "size") int size) {
        return resourceIdService.getPage(page, size);
    }
}
