grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
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
    	String springSecurityVersion = '3.2.0.RELEASE'
	String springWsVersion = '2.1.4.RELEASE'

        compile 'commons-httpclient:commons-httpclient:3.0.1'
        compile 'org.apache.ws.xmlschema:xmlschema-core:2.0.3'
        compile "org.springframework.security:spring-security-aspects:$springSecurityVersion"
        compile "org.springframework.security:spring-security-core:$springSecurityVersion"
        compile "org.springframework.security:spring-security-web:$springSecurityVersion"
        compile "org.springframework.ws:spring-ws:$springWsVersion"
        compile "org.springframework.ws:spring-ws-core:$springWsVersion"
        compile "org.springframework.ws:spring-ws-security:$springWsVersion", { excludes "wsit-rt", "xws-security" }
        compile "org.springframework.ws:spring-ws-support:$springWsVersion"
        compile "org.springframework.ws:spring-xml:$springWsVersion"
        compile 'net.sourceforge.htmlunit:htmlunit:2.9'
        //compile 'com.sun.xml.wsit:wsit-rt:1.1'
        compile ('com.sun.xml.stream:sjsxp:1.0.1') { excludes "stax-api" }

        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.16'
        //compile "joda-time:joda-time:2.0"
        //compile "joda-time:joda-time-hibernate:1.3"
        test("org.spockframework:spock-grails-support:0.7-groovy-2.0") { exclude 'groovy-all' }
    }

    plugins {
        compile ':functional-spock:0.6'
        compile ":hibernate:3.6.10.7"
        //compile ":jquery:1.7"
        //compile ":resources:1.1.3"

        build ":tomcat:7.0.41"
    }
}
