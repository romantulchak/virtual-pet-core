package com.virtualpet.transformer;

import com.virtualpet.dto.MoneyCurrencyDTO;
import com.virtualpet.dto.SubDTO;
import com.virtualpet.dto.SubTypeDTO;
import com.virtualpet.dto.UserDTO;
import com.virtualpet.dto.skill.DamageSkillDTO;
import com.virtualpet.dto.skill.DefenceSkillDTO;
import com.virtualpet.dto.skill.template.DamageSkillTemplateDTO;
import com.virtualpet.dto.skill.template.DefenceSkillTemplateDTO;
import com.virtualpet.dto.skill.template.SkillAbstractTemplateDTO;
import com.virtualpet.model.Sub;
import com.virtualpet.model.SubType;
import com.virtualpet.model.User;
import com.virtualpet.model.skills.template.DamageSkillTemplate;
import com.virtualpet.model.skills.template.SkillAbstractTemplate;
import com.virtualpet.model.sub.Currency;
import com.virtualpet.model.sub.Money;
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

    public UserDTO getUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public SubTypeDTO getSubTypeDTO(SubType subType){
        return modelMapper.map(subType, SubTypeDTO.class);
    }

    public MoneyCurrencyDTO getMoneyCurrencyDTO(Money money, Currency currency){
        return new MoneyCurrencyDTO(money, currency);
    }

    /**
     * Converts skills to DTO depends on the category (type)
     *
     * @param skillAbstractTemplate to be converted to DTO
     * @return DTO of skill {@link DamageSkillDTO} or {@link DefenceSkillDTO}
     */
    public SkillAbstractTemplateDTO getSkillTemplateDTO(SkillAbstractTemplate skillAbstractTemplate){
        SkillAbstractTemplateDTO skill;
        if (skillAbstractTemplate instanceof DamageSkillTemplate){
            skill = modelMapper.map(skillAbstractTemplate, DamageSkillTemplateDTO.class);
        }else{
            skill = modelMapper.map(skillAbstractTemplate, DefenceSkillTemplateDTO.class);
        }
        return skill;
    }
}
