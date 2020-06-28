package com.employee.api.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.employee.api.mapper.EmployeeEntityMapper;
import com.employee.api.mapper.EmployeeModelMapper;

/**
 * Bean creation for spring converter
 * @author akshay
 *
 */
@Configuration
public class ConverterConfig {

	/**This method returns spring converter bean
	 * @return
	 */
	@Bean(name="conversionService")
	public ConversionService getConversionServiceBean()
	{
		ConversionServiceFactoryBean conversionServiceFactoryBean=new ConversionServiceFactoryBean();
		Set<Converter<?,?>> converterSet=new HashSet<Converter<?,?>>();
		converterSet.add(new EmployeeEntityMapper());
		converterSet.add(new EmployeeModelMapper());
		conversionServiceFactoryBean.setConverters(converterSet);
		conversionServiceFactoryBean.afterPropertiesSet();
		return conversionServiceFactoryBean.getObject(); 
		
		
	}
	
}
