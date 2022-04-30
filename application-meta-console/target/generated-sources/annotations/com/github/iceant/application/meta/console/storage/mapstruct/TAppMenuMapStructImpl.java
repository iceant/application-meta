package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TAppMenuDTO;
import com.github.iceant.application.meta.console.storage.entity.TAppMenu;
import com.github.iceant.application.meta.console.storage.vo.TAppMenuVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-30T17:36:04+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class TAppMenuMapStructImpl implements TAppMenuMapStruct {

    @Override
    public TAppMenu dtoToEntity(TAppMenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TAppMenu tAppMenu = new TAppMenu();

        tAppMenu.setId( dto.getId() );
        tAppMenu.setName( dto.getName() );
        tAppMenu.setUserFriendlyName( map( dto.getUserFriendlyName() ) );
        tAppMenu.setDescription( map( dto.getDescription() ) );
        tAppMenu.setIcon( dto.getIcon() );
        tAppMenu.setPath( dto.getPath() );
        tAppMenu.setParentId( dto.getParentId() );
        tAppMenu.setCreationDatetime( dto.getCreationDatetime() );

        return tAppMenu;
    }

    @Override
    public TAppMenu merge(TAppMenuDTO dto, TAppMenu entity) {
        if ( dto == null ) {
            return null;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getUserFriendlyName() != null ) {
            entity.setUserFriendlyName( map( dto.getUserFriendlyName() ) );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( map( dto.getDescription() ) );
        }
        if ( dto.getIcon() != null ) {
            entity.setIcon( dto.getIcon() );
        }
        if ( dto.getPath() != null ) {
            entity.setPath( dto.getPath() );
        }
        if ( dto.getParentId() != null ) {
            entity.setParentId( dto.getParentId() );
        }
        if ( dto.getCreationDatetime() != null ) {
            entity.setCreationDatetime( dto.getCreationDatetime() );
        }

        return entity;
    }

    @Override
    public TAppMenuVO entityToVO(TAppMenu entity) {
        if ( entity == null ) {
            return null;
        }

        TAppMenuVO tAppMenuVO = new TAppMenuVO();

        tAppMenuVO.setId( entity.getId() );
        tAppMenuVO.setName( entity.getName() );
        tAppMenuVO.setUserFriendlyName( map( entity.getUserFriendlyName() ) );
        tAppMenuVO.setDescription( map( entity.getDescription() ) );
        tAppMenuVO.setIcon( entity.getIcon() );
        tAppMenuVO.setPath( entity.getPath() );
        tAppMenuVO.setParentId( entity.getParentId() );
        tAppMenuVO.setCreationDatetime( entity.getCreationDatetime() );

        return tAppMenuVO;
    }
}
