package com.github.iceant.application.meta.console.storage.controller;

import com.github.iceant.application.meta.console.utils.PrimaryKeyUtil;
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
import java.time.LocalDateTime;
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
@RequestMapping("/storage/tProtocolAccount")
public class TProtocolAccountController {

        final ITProtocolAccountService service;

        public TProtocolAccountController(ITProtocolAccountService service) {
            this.service = service;
        }

        @GetMapping(path = {"/", ""})
        public Object listAll(){
            List<TProtocolAccountVO> viewObjectList = TProtocolAccountMapStruct.INSTANCE.entityListToVOList(service.list());
            return ApiResponse.ok(viewObjectList);
        }

        @PostMapping(path={"/", ""})
        public Object create(@RequestBody TProtocolAccountDTO dto){
            TProtocolAccount entity = TProtocolAccountMapStruct.INSTANCE.dtoToEntity(dto);
            if(dto.getId()==null){
                entity.setId(PrimaryKeyUtil.nextId());
            }
            entity.setCreationDatetime(LocalDateTime.now());
            return ApiResponse.ok(service.save(entity));
        }

        @PutMapping(path = {"/", ""})
        public Object update(@RequestBody TProtocolAccountDTO dto){
            TProtocolAccount entity = TProtocolAccountMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.updateById(entity));
        }

        @DeleteMapping(path = {"/", ""})
        public Object delete(@RequestBody TProtocolAccountDTO dto){
            TProtocolAccount entity = TProtocolAccountMapStruct.INSTANCE.dtoToEntity(dto);
            return ApiResponse.ok(service.removeById(entity));
        }

        @GetMapping(path = {"/item/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TProtocolAccount entity = service.getById(id);
            TProtocolAccountVO viewObject = TProtocolAccountMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }
}
