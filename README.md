## FedEx Utils ##

**Note:** Register for API key here: https://developer.fedex.com/api/en-us/home.html

## Instantiate ##

```java
FedexApi fedexApi = new FedexApi(environment, clientId, clientSecret);
```

Environment (PRODUCTION, SANDBOX)

```java
FedexEnvironment environment = FedexEnvironment.SANDBOX;
```

Oauth Credentials

```java
String clientId = "*****************"; 
String clientSecret = "************"; 
```

## Authorize ##

```java
OauthResponse oauthResponse = fedexApi.authorize();
```

## API Token ##

```java
String accessToken = oauthResponse.getAccessToken();
```

## Track Package(s) ##

Generate Request

```java
TrackingRequest trackingRequest = TrackingFactory.basicRequest(
	"449044304137821", "149331877648230", "020207021381215"
);
```

**Note:** Fedex caps the number of tracking numbers to 30 per request.

Request

```java
TrackingResponse response = fedexApi.track(trackingRequest, accessToken);
```

## Tracking Status Codes ##

Tracking Status (scan event) codes are 2 character strings that can be decoded

```java
String description = TrackingStatusCodes.lookup(code).getDescription();
```

## Other ##

 - Sandbox test numbers: [Sandbox Test Tracking Numbers](https://www.fedex.com/us/developer/webhelp/ws/2021/US/FedEx_WebServices_2021_Developer_Guide.htm#t=wsdvg%2FAppendix_F_Test_Server_Mock_Tracking_Numbers.htm)
 - Web Service Documentation: [Web Service Developer Guide](https://www.fedex.com/us/developer/webhelp/ws/2021/US/FedEx_WebServices_2021_Developer_Guide.htm#t=wsdvg%2FAbout_This_Guide.htm)
  