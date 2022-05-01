package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TDataShapeDTO;
import com.github.iceant.application.meta.console.storage.vo.TDataShapeVO;
import com.github.iceant.application.meta.console.storage.entity.TDataShape;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TDataShapeMapStruct {
    TDataShapeMapStruct INSTANCE = Mappers.getMapper(TDataShapeMapStruct.class);

    TDataShape dtoToEntity(TDataShapeDTO dto);

    TDataShape merge(TDataShapeDTO dto, @MappingTarget TDataShape entity);

    TDataShapeVO entityToVO(TDataShape entity);

    List<TDataShapeVO> entityListToVOList(List<TDataShape> entityList);

    List<TDataShape> dtoListToEntityList(List<TDataShapeDTO> dtoList);

    default String map(LocaleString value){
        if(value==null) return null;
        return value.toString();
    }

    default LocaleString map(String value){
        if(value==null) return null;
        return LocaleString.fromString(value);
    }
}
