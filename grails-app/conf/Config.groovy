// An example of exporting the WSDL, in the simplest case, for the Holiday Endpoint
springws.wsdl.Holiday.export

grails.project.appName = "springws"

// A more complex wsdl export could take the form of:
//springws {
//    wsdl {
//        Holiday {
//            wsdlName= 'Holiday-v2'
//            xsds= '/hr/v2/schemas/Holiday.xsd'
//            portTypeName = 'HolidayPort'
//            serviceName = 'HolidayService'
//            locationUri = grails.serverURL + '/services/hr/v2/Holiday'
//            targetNamespace = 'http://mycompany.com/hr/v2/definitions'
//        }
//    }
//}

//force Spring Security to store passwords in clear text, which is not the default
// but necessary in digest username token schema (except if the server agrees with all
// of its client on a hashing scheme i.e. Base64 of SHA-1 of the digest password)
springws.security.springsecurity.clearTextPasswords=true

// Test example configuration for WS Security support
springws.security.keyStore.myKeyStore.location='file:grails-app/keys/mykeystore.jks'
springws.security.keyStore.myKeyStore.type='jks'
springws.security.keyStore.myKeyStore.password='123456'

// An example of what each the Spring WS security factors are
//springws.security.keyStore.myOtherKeyStore.location='file://'
//springws.security.keyStore.myOtherKeyStore.type=''
//springws.security.keyStore.myOtherKeyStore.password=''

// You can even declare all the security configuration in one statement if you prefer
//springws.security.keyStore.myOtherKeyStore{
//    location='file://'
//    type=''
//    password=''
//}

// We could also export a static WSDL
springws.wsdl.StaticWsdl.file = '/WEB-INF/static-wsdl.wsdl'

// log4j configuration
log4j = {
    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails."web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails."web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate'

    debug 'SpringwsGrailsPlugin'
    trace 'org.springframework.ws.client.MessageTracing'
}

grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"


// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
