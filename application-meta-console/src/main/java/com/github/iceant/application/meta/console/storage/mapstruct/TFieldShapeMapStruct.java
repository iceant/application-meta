package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TFieldShapeDTO;
import com.github.iceant.application.meta.console.storage.vo.TFieldShapeVO;
import com.github.iceant.application.meta.console.storage.entity.TFieldShape;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TFieldShapeMapStruct {
    TFieldShapeMapStruct INSTANCE = Mappers.getMapper(TFieldShapeMapStruct.class);

    TFieldShape dtoToEntity(TFieldShapeDTO dto);

    TFieldShape merge(TFieldShapeDTO dto, @MappingTarget TFieldShape entity);

    TFieldShapeVO entityToVO(TFieldShape entity);

    List<TFieldShapeVO> entityListToVOList(List<TFieldShape> entityList);

    List<TFieldShape> dtoListToEntityList(List<TFieldShapeDTO> dtoList);

    default String map(LocaleString value){
        if(value==null) return null;
        return value.toString();
    }

    default LocaleString map(String value){
        if(value==null) return null;
        return LocaleString.fromString(value);
    }
}
