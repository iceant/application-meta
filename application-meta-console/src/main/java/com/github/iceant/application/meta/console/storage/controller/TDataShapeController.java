package com.github.iceant.application.meta.console.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
@RequestMapping("/storage/tDataShape")
public class TDataShapeController {

        final ITDataShapeService service;
        final ITFieldShapeService fieldShapeService;

        public TDataShapeController(ITDataShapeService service, ITFieldShapeService fieldShapeService) {
            this.service = service;
            this.fieldShapeService = fieldShapeService;
        }

        @GetMapping(path = {"/", ""})
        public Object listAll(){
            List<TDataShapeVO> viewObjectList = TDataShapeMapStruct.INSTANCE.entityListToVOList(service.list());
            return ApiResponse.ok(viewObjectList);
        }

        @PostMapping(path={"/", ""})
        public Object create(@RequestBody TDataShapeDTO dto){
            TDataShape entity = TDataShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.save(entity));
        }

        @PutMapping(path = {"/", ""})
        public Object update(@RequestBody TDataShapeDTO dto){
            TDataShape entity = TDataShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.updateById(entity));
        }

        @DeleteMapping(path = {"/", ""})
        public Object delete(@RequestBody TDataShapeDTO dto){
            TDataShape entity = TDataShapeMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.removeById(entity));
        }

        @GetMapping(path = {"/item/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TDataShape entity = service.getById(id);
            TDataShapeVO viewObject = TDataShapeMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }

        @GetMapping(path={"/item/{id}/fields"})
        public Object fieldsList(PageDTO<TFieldShape> page, @PathVariable("id") Serializable id){
            QueryWrapper<TFieldShape> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(TFieldShape.DATA_SHAPE_ID, id);
            IPage<TFieldShape> fieldShapes = fieldShapeService.page(page, queryWrapper);
            List<TFieldShapeVO> voList = TFieldShapeMapStruct.INSTANCE.entityListToVOList(fieldShapes.getRecords());
            Page<TFieldShapeVO> resultPage = new Page<>(fieldShapes.getCurrent(), fieldShapes.getSize(), fieldShapes.getTotal(), fieldShapes.searchCount());
            resultPage.setRecords(voList);
            return ApiResponse.ok(resultPage);
        }
}
