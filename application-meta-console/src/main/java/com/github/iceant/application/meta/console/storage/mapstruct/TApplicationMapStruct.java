package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TApplicationDTO;
import com.github.iceant.application.meta.console.storage.vo.TApplicationVO;
import com.github.iceant.application.meta.console.storage.entity.TApplication;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TApplicationMapStruct {
    TApplicationMapStruct INSTANCE = Mappers.getMapper(TApplicationMapStruct.class);

    TApplication dtoToEntity(TApplicationDTO dto);

    TApplication merge(TApplicationDTO dto, @MappingTarget TApplication entity);

    TApplicationVO entityToVO(TApplication entity);

    List<TApplicationVO> entityListToVOList(List<TApplication> entityList);

    List<TApplication> dtoListToEntityList(List<TApplicationDTO> dtoList);

    default String map(LocaleString value){
        if(value==null) return null;
        return value.toString();
    }

    default LocaleString map(String value){
        if(value==null) return null;
        return LocaleString.fromString(value);
    }
}
