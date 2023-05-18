package com.example.railway.beanConfig;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
   @Bean
   public ModelMapper modelMapper() {
       ModelMapper modelMapper = new ModelMapper();
       modelMapper.getConfiguration()
               .setMatchingStrategy(MatchingStrategies.STRICT)
               .setPropertyCondition(Conditions.isNotNull());
       return modelMapper;
   }
}

