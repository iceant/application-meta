package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TDataTypeDTO;
import com.github.iceant.application.meta.console.storage.vo.TDataTypeVO;
import com.github.iceant.application.meta.console.storage.entity.TDataType;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TDataTypeMapStruct {
    TDataTypeMapStruct INSTANCE = Mappers.getMapper(TDataTypeMapStruct.class);

    TDataType dtoToEntity(TDataTypeDTO dto);

    TDataType merge(TDataTypeDTO dto, @MappingTarget TDataType entity);

    TDataTypeVO entityToVO(TDataType entity);

    List<TDataTypeVO> entityListToVOList(List<TDataType> entityList);

    List<TDataType> dtoListToEntityList(List<TDataTypeDTO> dtoList);

    default String map(LocaleString value){
        if(value==null) return null;
        return value.toString();
    }

    default LocaleString map(String value){
        if(value==null) return null;
        return LocaleString.fromString(value);
    }
}
