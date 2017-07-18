package com.epam.theatre.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;


import com.epam.theatre.rest.parser.ListTicketHttpMessageConverterImpl;

@EnableWebMvc
@Configuration
@ComponentScan("com.epam.theatre")
@Import({ AppConfigCommon.class })
public class AppConfigAdmin extends WebMvcConfigurerAdapter {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(20848820);
		return resolver;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		List<View> list = new ArrayList<>();
		list.add((View) new PdfViewResolver());
		resolver.setOrder(1);
		resolver.setDefaultViews(list);
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/**/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}


  @Bean
  public RestTemplate restTemplate() {
   RestTemplate restTemplate = new RestTemplate();
   List<HttpMessageConverter<?>> converter = new ArrayList<HttpMessageConverter<?>>();
   converter.add(converter());
   converter.add(listTicketHttpMessageConverterImpl());
   converter.add(byteArrayHttpMessageConverter());
   restTemplate.setMessageConverters(converter);


    return restTemplate;
  }
	
  @Bean
	public ByteArrayHttpMessageConverter  byteArrayHttpMessageConverter() {
		return new ByteArrayHttpMessageConverter();
	}
	
	
	@Bean
	public ListTicketHttpMessageConverterImpl  listTicketHttpMessageConverterImpl() {
		return new ListTicketHttpMessageConverterImpl();
	}

	@Bean
	public MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}
	
	//soap
	
	@Bean
	public Jaxb2Marshaller marshaller() {
	Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
	jaxb2Marshaller.setPackagesToScan(
			"com.epam.theatre.soap.endpoint,"
			+ "com.epam.theatre.soap.event,"
			+ "com.epam.theatre.soap.customer,"
            +"com.epam.theatre.domain"
	);
       
	return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
	    WebServiceTemplate template = new WebServiceTemplate();
	    try {
	        template.setMarshaller(marshaller());
	        template.setUnmarshaller(marshaller());
	        template.setDefaultUri("http://localhost:8080/mvc/ws");
	        HttpComponentsMessageSender sender = new HttpComponentsMessageSender();
	        sender.afterPropertiesSet();
	        template.setMessageSender(sender);
	    } catch (Exception e) {
	        // @todo: handle me
	    }
	    return template;
	}
	
	  @Bean
      public SaajSoapMessageFactory messageFactory() {
          SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
          factory.setSoapVersion(SoapVersion.SOAP_11);
          return factory;
      }
	  
//	  @Bean(name = "EventServiceImplService")
//      public XsdSchema getEventServiceImplService() {
//          return new SimpleXsdSchema(new ClassPathResource("WEB-INF/ws/EventServiceImplService.xsd"));
//      }
//	  
//	  @Bean(name = "UserServiceImplService")
//      public XsdSchema getUserServiceImplService() {
//          return new SimpleXsdSchema(new ClassPathResource("WEB-INF/ws/UserServiceImplService.xsd"));
//      }


}