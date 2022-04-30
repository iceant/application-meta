package com.github.iceant.application.meta.console.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.iceant.application.meta.console.storage.entity.*;
import com.github.iceant.application.meta.console.storage.mapstruct.*;
import com.github.iceant.application.meta.console.storage.service.*;
import com.github.iceant.application.meta.console.storage.dto.*;
import com.github.iceant.application.meta.console.storage.vo.*;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen Peng
 * @since 2022-04-30
 */
@RestController
@RequestMapping("/storage/tAppMenu")
public class TAppMenuController {

        final ITAppMenuService service;

        public TAppMenuController(ITAppMenuService service) {
            this.service = service;
        }

        @GetMapping(path = {"/", ""})
        public Object listAll(){
            return ApiResponse.ok(service.list());
        }

        @PostMapping(path={"/", ""})
        public Object create(@RequestBody TAppMenuDTO dto){
            TAppMenu entity = TAppMenuMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.save(entity));
        }

        @PutMapping(path = {"/", ""})
        public Object update(@RequestBody TAppMenuDTO dto){
            TAppMenu entity = TAppMenuMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.updateById(entity));
        }

        @DeleteMapping(path = {"/", ""})
        public Object delete(@RequestBody TAppMenuDTO dto){
            TAppMenu entity = TAppMenuMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.removeById(entity));
        }

        @GetMapping(path = {"/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TAppMenu entity = service.getById(id);
            TAppMenuVO viewObject = TAppMenuMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }
}
