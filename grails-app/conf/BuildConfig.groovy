grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits "global"
    log "warn"
    checksums true

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenCentral()
        mavenLocal()
    }
    dependencies {
        compile 'commons-httpclient:commons-httpclient:3.0.1'
        compile 'org.apache.ws.xmlschema:xmlschema-core:2.0.3'
        compile 'org.springframework.security:spring-security-aspects:3.1.3.RELEASE'
        compile 'org.springframework.security:spring-security-core:3.1.3.RELEASE'
        compile 'org.springframework.security:spring-security-web:3.1.3.RELEASE'
        compile 'org.springframework.ws:spring-ws:2.1.3.RELEASE'
        compile 'org.springframework.ws:spring-ws-core:2.1.3.RELEASE'
        compile('org.springframework.ws:spring-ws-security:2.1.3.RELEASE') { excludes "wsit-rt", "xws-security" }
        compile 'org.springframework.ws:spring-ws-support:2.1.3.RELEASE'
        compile 'org.springframework.ws:spring-xml:2.1.3.RELEASE'
        compile 'net.sourceforge.htmlunit:htmlunit:2.9'
        //compile 'com.sun.xml.wsit:wsit-rt:1.1'
        compile ('com.sun.xml.stream:sjsxp:1.0.1') { excludes "stax-api" }

        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
        //compile "joda-time:joda-time:2.0"
        //compile "joda-time:joda-time-hibernate:1.3"
    }

    plugins {
        compile ":functional-test:2.0.M2"
        compile ":hibernate:latest.release"
        //compile ":jquery:1.7"
        //compile ":resources:1.1.3"

        build ":tomcat:7.0.41"
    }
}
