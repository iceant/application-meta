package com.github.iceant.application.meta.console.storage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.iceant.application.meta.console.storage.dto.TAppMenuDTO;
import com.github.iceant.application.meta.console.storage.entity.TAppMenu;
import com.github.iceant.application.meta.console.storage.mapstruct.TAppMenuMapStruct;
import com.github.iceant.application.meta.console.storage.service.ITAppMenuService;
import com.github.iceant.application.meta.console.storage.vo.TAppMenuVO;
import com.github.iceant.application.meta.console.utils.PrimaryKeyUtil;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
            if(dto.getId()==null) {
                entity.setId(PrimaryKeyUtil.nextId());
            }
            entity.setCreationDatetime(LocalDateTime.now());
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

        @GetMapping(path = {"/item/{id}"})
        public Object get(@PathVariable("id") Serializable id){
            TAppMenu entity = service.getById(id);
            TAppMenuVO viewObject = TAppMenuMapStruct.INSTANCE.entityToVO(entity);
            return ApiResponse.ok(viewObject);
        }

        @GetMapping(path = {"/firstLevel"})
        public Object firstLevel(){
            QueryWrapper<TAppMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(TAppMenu.PARENT_ID, 0);
            List<TAppMenu> menus = service.list(queryWrapper);
            List<TAppMenuVO> voList = TAppMenuMapStruct.INSTANCE.entityListToVOList(menus);
            return ApiResponse.ok(voList);
        }

        @GetMapping(path = {"/{id}/subMenus"})
        public Object subMenus(@PathVariable("id") Serializable id){
            QueryWrapper<TAppMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(TAppMenu.PARENT_ID, id);
            queryWrapper.orderByAsc(TAppMenu.NAME);
            List<TAppMenu> menus = service.list(queryWrapper);
            List<TAppMenuVO> voList = TAppMenuMapStruct.INSTANCE.entityListToVOList(menus);
            return ApiResponse.ok(voList);
        }
}
