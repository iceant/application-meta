package com.github.iceant.application.meta.console.storage.mapstruct;

import com.github.iceant.application.meta.console.storage.dto.TAppMenuDTO;
import com.github.iceant.application.meta.console.storage.entity.TAppMenu;
import com.github.iceant.application.meta.console.storage.vo.TAppMenuVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-03T21:02:37+0800",
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
        tAppMenu.setUserFriendlyName( dto.getUserFriendlyName() );
        tAppMenu.setDescription( dto.getDescription() );
        tAppMenu.setIcon( dto.getIcon() );
        tAppMenu.setPath( dto.getPath() );
        tAppMenu.setView( dto.getView() );
        tAppMenu.setParentId( dto.getParentId() );
        tAppMenu.setOrderIndex( dto.getOrderIndex() );
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
            entity.setUserFriendlyName( dto.getUserFriendlyName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        if ( dto.getIcon() != null ) {
            entity.setIcon( dto.getIcon() );
        }
        if ( dto.getPath() != null ) {
            entity.setPath( dto.getPath() );
        }
        if ( dto.getView() != null ) {
            entity.setView( dto.getView() );
        }
        if ( dto.getParentId() != null ) {
            entity.setParentId( dto.getParentId() );
        }
        if ( dto.getOrderIndex() != null ) {
            entity.setOrderIndex( dto.getOrderIndex() );
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
        tAppMenuVO.setUserFriendlyName( entity.getUserFriendlyName() );
        tAppMenuVO.setDescription( entity.getDescription() );
        tAppMenuVO.setIcon( entity.getIcon() );
        tAppMenuVO.setPath( entity.getPath() );
        tAppMenuVO.setView( entity.getView() );
        tAppMenuVO.setParentId( entity.getParentId() );
        tAppMenuVO.setOrderIndex( entity.getOrderIndex() );
        tAppMenuVO.setCreationDatetime( entity.getCreationDatetime() );

        return tAppMenuVO;
    }

    @Override
    public List<TAppMenuVO> entityListToVOList(List<TAppMenu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TAppMenuVO> list = new ArrayList<TAppMenuVO>( entityList.size() );
        for ( TAppMenu tAppMenu : entityList ) {
            list.add( entityToVO( tAppMenu ) );
        }

        return list;
    }

    @Override
    public List<TAppMenu> dtoListToEntityList(List<TAppMenuDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TAppMenu> list = new ArrayList<TAppMenu>( dtoList.size() );
        for ( TAppMenuDTO tAppMenuDTO : dtoList ) {
            list.add( dtoToEntity( tAppMenuDTO ) );
        }

        return list;
    }
}
