# GraphGrid Java SDK

# Release Notes


# Getting Started 

**Using the SDK Maven modules** 

core module is required 
```pom
        <!-- core module required for configuring sdk -->
        <dependency>
            <groupId>com.graphgrid</groupId>
            <artifactId>graphgrid-sdk-java-core</artifactId>
            <version>${project.version}</version>
        </dependency>       

 ```       
client/service module should be added as needed   
```pom
        <dependency>
            <groupId>com.graphgrid</groupId>
            <artifactId>graphgrid-sdk-java-files</artifactId>
            <version>${project.version}</version>
        </dependency>        
```
   
**Configuring with Spring Framework** 

its recommended to use a Spring Configuration Class and create client services as Spring Beans
 
adding the security configuration that is needed for authentication 
```java  
@Configuration
public class AppGraphGridSdkConfig
{
    
    @Value( "${spring.security.base.url}" )
    private String springOauthTokenUrl;

    @Value( "${spring.oauth.client.id}" )
    private String oAuthClientId;

    @Value( "${spring.oauth.client.secret}" )
    private String oAuthClientSecret;

    @Bean
    public SessionFactory ggTokenSession()
    {
        return new SpringSecurityContextTokenFactory( () -> SecurityContextHolder.getContext().getAuthentication().getDetails() );
    }

    @Bean
    public SecurityConfig ggSecurityConfig()
    {
        final SecurityConfig securityConfig = new SecurityConfig();
        securityConfig.setClientSecret( oAuthClientSecret );
        securityConfig.setClientId( oAuthClientId );
        securityConfig.setBaseSecurityUrl( springOauthTokenUrl );
        return securityConfig;
    }
}
```   
SessionFactory ggTokenSession() acts as an interceptor that will automatically add the token of a REST request to the header of call made by the SDK.
Although its not required its recommended to add it for monitoring purpose.


a client module can be added as follows: 

```java    
       @Value( "${spring.services.files.url}" )
       private String filesEndpoint;
       
       @Bean
       public GraphGridFilesClient graphGridFilesClient()
       {
           return new GraphGridFilesClient( filesEndpoint, ggSecurityConfig(), ggTokenSession() );
       }
```

the `graphGridFilesClient`  can now be `@Autowired` in any spring service

```java
    @Autowired
    private GraphGridFilesClient graphGridFilesClient;
```

By default all requests made with the sdk will get and reuse a token based on the security credentials the client was configured with.
However its possible to overwrite those for an individual call by overwriting AuthMethod of the request.


```java
//use a specific token
new GraphGridServiceRequest(  ).withAuthMethod( new TokenRequest( "123456-1234-1234-1234" ) ) 

// in case there is no auth required or for testing purpose
new GraphGridServiceRequest(  ).withAuthMethod( new NoTokenRequest() )

```




# Developer Guidelines


