package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TAppMenuDTO;
import com.github.iceant.application.meta.console.storage.vo.TAppMenuVO;
import com.github.iceant.application.meta.console.storage.entity.TAppMenu;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TAppMenuMapStruct {
    TAppMenuMapStruct INSTANCE = Mappers.getMapper(TAppMenuMapStruct.class);

    TAppMenu dtoToEntity(TAppMenuDTO dto);

    TAppMenu merge(TAppMenuDTO dto, @MappingTarget TAppMenu entity);

    TAppMenuVO entityToVO(TAppMenu entity);

    default String map(LocaleString value){
        return value.toString();
    }

    default LocaleString map(String value){
        return LocaleString.fromString(value);
    }
}
