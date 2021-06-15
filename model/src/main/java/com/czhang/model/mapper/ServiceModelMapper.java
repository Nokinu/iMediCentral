package com.czhang.model.mapper;

import com.czhang.model.model.MediCenterSet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface ServiceModelMapper {
   void updateMediCenterSets(MediCenterSet org, @MappingTarget MediCenterSet target);
}
