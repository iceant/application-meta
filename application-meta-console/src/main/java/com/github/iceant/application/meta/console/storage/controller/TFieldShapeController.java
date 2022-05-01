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
 * @since 2022-05-01
 */
@RestController
@RequestMapping("/storage/tFieldShape")
public class TFieldShapeController {

        final ITFieldShapeService service;

        public TFieldShapeController(ITFieldShapeService service) {
            this.service = service;
        }

        @GetMapping(path = {"/", ""})
        public Object listAll(){
            List<TFieldShapeVO> viewObjectList = TFieldShapeMapStruct.INSTANCE.entityListToVOList(service.list());
            return ApiResponse.ok(viewObjectList);
        }

        @PostMapping(path={"/", ""})
        public Object create(@RequestBody TFieldShapeDTO dto){
            TFieldShape entity = TFieldShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.save(entity));
        }

        @PutMapping(path = {"/", ""})
        public Object update(@RequestBody TFieldShapeDTO dto){
            TFieldShape entity = TFieldShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.updateById(entity));
        }

        @DeleteMapping(path = {"/", ""})
        public Object delete(@RequestBody TFieldShapeDTO dto){
            TFieldShape entity = TFieldShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.removeById(entity));
        }

        @GetMapping(path = {"/item/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TFieldShape entity = service.getById(id);
            TFieldShapeVO viewObject = TFieldShapeMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }
}
