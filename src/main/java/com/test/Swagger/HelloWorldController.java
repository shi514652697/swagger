package com.test.Swagger;

import java.net.URI;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HelloWorldController {
	
	
	@Value("${swaggerUI.enable:true}")
	boolean enbaleSwaggerUI;
	
	@GetMapping(path="/hello")
	public String getHelloWorld()
	{
		return "hello world";
		
	}
	


	@Autowired
	RestTemplate restTemplate;
	
	//@Autowired
	//DiscoveryClient discoveryClient;
	
	//@Value("#{null)")
	private Random random;
	
	@PostMapping(value="/private/v1/eod/details/retrieve", produces= {"application/json"}, consumes= {"application/json"})
	public ResponseEntity<?> generateEODReport(@RequestHeader(value="uuid",required=true) String uuid,@RequestHeader(
			value="channelId", defaultValue="RTLAO", required=false) String channelId, @RequestBody Product product)
	{
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("uuid", uuid);
		headers.add("channelId", channelId);
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		
		HttpEntity<Product> requestEntity = new HttpEntity<>(product,headers);
		
		URI serviceUri= getURI("COUPON-SERVICE", "/private/v1/eod/details/retrieve");
		
		if(null != serviceUri)
		{
			ResponseEntity<String> result = restTemplate.exchange(serviceUri.toString(), HttpMethod.POST, requestEntity, String.class);
			result.getBody();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	private URI getURI(String serviceName, String endPoint) {
		String serviceUrl = getServiceUrl(serviceName);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl+ endPoint);
		URI uri = builder.build().encode().toUri();
		
		return uri;
	}
	
	
	private URI getPramsURI(String serviceName, String endPoint,Map<String,String> paramMap) {
		String serviceUrl = getServiceUrl(serviceName);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl+ endPoint);
		URI uri = builder.buildAndExpand(paramMap).encode().toUri();
		
		return uri;
	}
	

	private String getServiceUrl(String serviceName) {
		String serviceUrl = "";
		if("activeProfile".contains("LOCAL"))
		{
			if(serviceName.equals("COUPON-SERVICE"))
			{
				serviceUrl = "http://localhost:8080";
			}
		}
		else
		{
			/*List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
			if(null != instances)
			{
				ServiceInstance instance = instances.get(random.nextInt(instances.size()));
				if(null != instance)
					return instance.getUri().toString();
			}*/
			
		}
		return null;
	}
	
	
	
	
	@ApiOperation(value="This API is used to retrieve eod report", notes="Tranactions will be saved in DB",
			response= String.class, tags= {"USRAO"})
	@ApiResponses(value= {
		@ApiResponse(code=200, message="Successfull response", response=EodReportDto.class)	,
		@ApiResponse(code= 201, message="<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>Success</td><td>AccountOpenedNotSaved</td><td>Accounts created but application not saved in DB</td></tr><tr><td>Success</td><td>AccountNotOpned</td><td>Account can not be opened due to system issue</td></tr><tr><td>Sucess</td><td>AccountNotSavedNotOpned</td><td>Account is not opned and not saved in DB</td></tr></table>", response = String.class),
		@ApiResponse(code= 400, message="<table><tr><td>Type</td><td>Code</td><td>Details</td></tr><tr><td>Invalid</td><td>Invalid Request</td><td>Missing or invalid parameters</td></tr><tr><td>Error</td><td>Validator Error</td><td>Validation Error</td></tr></table>", response = ErrorResponseDto.class)
	})
	@GetMapping(value="/crud/eodreport/{rportDate}/{channelId}/{branchId}", produces= {"application/json"}, consumes= {"application/json"})
	public ResponseEntity<EodReportDto> retrieveEodReport(
			@ApiParam(value="Unique 128 bit random UUID gerated uniquly for every request", required=true)
			@RequestHeader(value="uuid", required=true)
			String uuid,
			
			@ApiParam(value="Content Type", required=false)
			@RequestHeader(value="Accept", required=false, defaultValue="application/json")
			String accept,
			
			@ApiParam(value="List of acceptable laungauge", required=false)
			@RequestHeader(value="Accept-Language", required=false, defaultValue="application/json")
			String acceptLanguage,
			
			@ApiParam(value="Business Code", required=true)
			@RequestHeader(value="BusinessCode", required=true, defaultValue="GCB")
			String businessCode,
			
			@ApiParam(value="Report Date to get EOD report", required=true)
			@PathVariable(value="reportDate", required=true)
			String reportDate)
	{
		
		EodReportDto response= new EodReportDto();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	

}
