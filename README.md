# AlphaTraderJavaRestApiLib
[![Build Status](https://travis-ci.org/Alpha-Trader/AlphaTraderJavaRestApiLib.svg?branch=master)](https://travis-ci.org/Alpha-Trader/AlphaTraderJavaRestApiLib) [![GPL Licence](https://badges.frapsoft.com/os/gpl/gpl.png?v=103)](https://opensource.org/licenses/GPL-3.0/) [![codebeat badge](https://codebeat.co/badges/b3389414-f450-454b-9f19-2031ecbfe911)](https://codebeat.co/projects/github-com-alpha-trader-alphatraderjavarestapilib) [SonarQube Analysis](https://sonarqube.com/dashboard?id=com.alphatrader.rest%3Ajava-lib "Sonarqube")

Java library for interacting with the alpha trader rest api

## Usage
To use the library, add the following repository to your pom.xml

    <repositories>
        <repository>
            <id>alpha-trader-repository</id>
            <url>https://raw.githubusercontent.com/Alpha-Trader/maven-repository/master/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>
    
And then this dependency:

    <dependency>                                                                
        <groupId>com.alphatrader.rest</groupId>                                                  
        <artifactId>atrest-java</artifactId>                                            
        <version>1.0.0</version>                                                   
    </dependency>

The library needs to be initialized by providing a partnerId and a login like so:

    ApiLibConfig config = ApiLibConfig.getInstance();
    config.setPartnerId("<YourPartnerId>");
    User user = new User("<username>", "<password>");
    user.login();
    config.setUser(user);
    
After these lines, you can use any function of the library as the logged in user. For example, the following code fetches all companies in the game:

    List<Company> allCompanies = Company.getAllCompanies();

Almost all of the data classes feature static functions you can use to retreive objects from the API. Version 1.0.0 only features read-only API access. Stay tuned for more features in upcoming versions. The javadocs for version 1.0.0 are available here: [Javadocs](https://alpha-trader.github.io/AlphaTraderJavaRestApiLib/)

If you want to use a different API url, you can set it by calling setApiUrl() on the config object.

Happy coding!
