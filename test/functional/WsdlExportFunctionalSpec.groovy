import javax.wsdl.factory.WSDLFactory
import javax.xml.namespace.QName

import spock.lang.Specification

/*
* Copyright 2008-2009 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/**
  * Testing generated and static WSDLs exposed through Config
  *
  * @author Brian Sanders (brian_sanders@aon.com)
  *
  */
class WsdlExportFunctionalSpec extends Specification {

    def baseUrl = new URL('http://localhost:8080/springws/services/')

    def readWsdl(String relativeUrl) {
        def wsdl = new URL(baseUrl, relativeUrl)
        def r = WSDLFactory.newInstance().newWSDLReader()
        r.setFeature 'javax.wsdl.importDocuments', false
        r.readWSDL(wsdl.toString())
    }

    def "test the static wsdl"() {
        when:
        def definition = readWsdl('StaticWsdl/StaticWsdl.wsdl')
        
        then:
        def tns = definition.targetNamespace
        definition.messages[new QName(tns, 'GetTradePriceInput')]
        definition.messages[new QName(tns, 'GetTradePriceOutput')]
        definition.portTypes[new QName(tns, 'StockQuotePortType')]
        definition.bindings[new QName(tns, 'StockQuoteSoapBinding')]
        definition.services[new QName(tns, 'StockQuoteService')]
    }

    def "test the generated wsdl"() {
        when:
        def definition = readWsdl('Holiday/Holiday.wsdl')
        
        then:
        def tns = definition.targetNamespace
        definition.messages[new QName(tns, 'HolidayRequest')]
        definition.portTypes[new QName(tns, 'HolidayPort')]
        definition.services[new QName(tns, 'HolidayService')]
    }
}
