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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen Peng
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/storage/tApplication")
public class TApplicationController {

        final ITApplicationService service;

        public TApplicationController(ITApplicationService service) {
            this.service = service;
        }

        @GetMapping(path = {"/", ""})
        public Object listAll(){
            List<TApplicationVO> viewObjectList = TApplicationMapStruct.INSTANCE.entityListToVOList(service.list());
            return ApiResponse.ok(viewObjectList);
        }

        @PostMapping(path={"/", ""})
        public Object create(@RequestBody TApplicationDTO dto){
            TApplication entity = TApplicationMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.save(entity));
        }

        @PutMapping(path = {"/", ""})
        public Object update(@RequestBody TApplicationDTO dto){
            TApplication entity = TApplicationMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.updateById(entity));
        }

        @DeleteMapping(path = {"/{id}"})
        public Object delete(@PathVariable("id") Serializable id){
            boolean result = service.removeById(id);
            if(result){
                return ApiResponse.ok(result);
            }else{
                return ApiResponse.of(404, id, "NOT FOUND");
            }
        }

        @GetMapping(path = {"/item/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TApplication entity = service.getById(id);
            TApplicationVO viewObject = TApplicationMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }
}
