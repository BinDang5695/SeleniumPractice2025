# FinalProjectAutomationTestAnhTester
CRM Anh Tester Project

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.anhtester</groupId>
    <artifactId>FinalProjectAutomationTest2</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.34.0</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.17</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>2.0.17</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.4.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.4.1</version>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.20.0</version>
        </dependency>

        <dependency>
            <groupId>com.github.stephenc.monte</groupId>
            <artifactId>monte-screen-recorder</artifactId>
            <version>0.7.7.0</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.25.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.25.1</version>
        </dependency>

        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.1.2</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.24</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.38</version>
        </dependency>

        <!-- RestAssured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.5.6</version>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>5.5.6</version>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.13.1</version>
        </dependency>

        <dependency>
            <groupId>net.datafaker</groupId>
            <artifactId>datafaker</artifactId>
            <version>2.4.4</version>
        </dependency>

        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-testng</artifactId>
            <version>2.29.1</version>
        </dependency>

        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-attachments</artifactId>
            <version>2.29.1</version>
        </dependency>

        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-rest-assured</artifactId>
            <version>2.29.1</version>
        </dependency>

        <dependency>
            <groupId>net.sourceforge.tess4j</groupId>
            <artifactId>tess4j</artifactId>
            <version>5.16.0</version>
        </dependency>

    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.5.2</version>
                    <configuration>
                        <suiteXmlFiles>
                            <suiteXmlFile>src/test/resources/suites/UI_SuiteRegressionTest.xml</suiteXmlFile>
                            <!--suiteXmlFile>src/test/resources/suites/UI_ATEPractice.xml</suiteXmlFile/>-->
                        </suiteXmlFiles>
                        <argLine>
                            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.22.1/aspectjweaver-1.9.22.1.jar"
                        </argLine>
                        <testFailureIgnore>true</testFailureIgnore>
                        <systemPropertyVariables>
                            <allure.results.directory>target/allure-results</allure.results.directory>
                        </systemPropertyVariables>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>

</project>
