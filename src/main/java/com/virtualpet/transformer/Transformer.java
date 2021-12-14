package com.virtualpet.transformer;

import com.virtualpet.dto.SubDTO;
import com.virtualpet.model.Sub;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Transformer {

    private final ModelMapper modelMapper;

    public SubDTO getSubDTO(Sub sub){
        return modelMapper.map(sub, SubDTO.class);
    }

}
