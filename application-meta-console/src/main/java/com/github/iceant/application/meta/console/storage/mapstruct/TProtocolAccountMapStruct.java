package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TProtocolAccountDTO;
import com.github.iceant.application.meta.console.storage.vo.TProtocolAccountVO;
import com.github.iceant.application.meta.console.storage.entity.TProtocolAccount;
import com.github.iceant.application.meta.console.domain.LocaleString;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TProtocolAccountMapStruct {
    TProtocolAccountMapStruct INSTANCE = Mappers.getMapper(TProtocolAccountMapStruct.class);

    TProtocolAccount dtoToEntity(TProtocolAccountDTO dto);

    TProtocolAccount merge(TProtocolAccountDTO dto, @MappingTarget TProtocolAccount entity);

    TProtocolAccountVO entityToVO(TProtocolAccount entity);

    List<TProtocolAccountVO> entityListToVOList(List<TProtocolAccount> entityList);

    List<TProtocolAccount> dtoListToEntityList(List<TProtocolAccountDTO> dtoList);

    default String map(LocaleString value){
        if(value==null) return null;
        return value.toString();
    }

    default LocaleString map(String value){
        if(value==null) return null;
        return LocaleString.fromString(value);
    }
}
