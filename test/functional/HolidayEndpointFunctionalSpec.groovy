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

import org.codehaus.groovy.grails.plugins.spring.ws.EndpointFunctionalTestSupport
import org.springframework.ws.soap.client.SoapFaultClientException

import spock.lang.Specification

/**
 * An example of testing the Holiday endpoint and applied interceptors
 * with the full web service stack in place.
 *
 * @author Russ Miles (russ@russmiles.com)
 * @author Okke Tijhuis (o.tijhuis@gmail.com)
 *
 */
class HolidayEndpointFunctionalSpec extends Specification {

    final serviceURL = "http://localhost:8080/springws/services"
    final namespace = "http://mycompany.com/hr/schemas"

    def EndpointFunctionalTestSupport support

    def setup() {
        support = new EndpointFunctionalTestSupport()
    }

    def "test SOAP Document service"() {
        when:
        def response = new XmlSlurper().parseText support.withEndpointRequest(serviceURL) {
            HolidayRequest(xmlns: namespace) {
                Holiday {
                    StartDate("2006-07-03")
                    EndDate("2006-07-07")
                }
                Employee {
                    Number("42")
                    FirstName("Russ")
                    LastName("Miles")
                }
            }
        }

        then:
        def status = response.status
        status == "complete"
    }

    def static results = []

    /*
     * Added test to cause a concurrency bug in DefaultEndpointAdapter discovered in version 0.2. 
     * This causes a fatal thread based error on the server without the fix.
     */
    def "test multiple concurrent SOAP Document service invocations"() {
        given:
        def threads = []

        when:
        (1..10).each {
            threads.add(Thread.start {
                def response = new XmlSlurper().parseText support.withEndpointRequest(serviceURL) {
                    HolidayRequest(xmlns: namespace) {
                        Holiday {
                            StartDate("2006-07-03")
                            EndDate("2006-07-07")
                        }
                        Employee {
                            Number("42")
                            FirstName("Russ")
                            LastName("Miles")
                        }
                    }
                }
                addResult(response.status)
            })
        }

        then:"Following is needed since jUnit doesn't wait for the threads to finish so you can't see any output from the threads otherwise"
        threads.each { it.join() }
        println results
        results == [
            "complete",
            "complete",
            "complete",
            "complete",
            "complete",
            "complete",
            "complete",
            "complete",
            "complete",
            "complete"
        ]
    }

    synchronized addResult(result) {
        results.add(result)
    }

    /*
     * Test the incoming payload document validation interceptor
     *
     */
    def "test SOAP Document service validation interceptor"() {
        when:
        def response = new XmlSlurper().parseText support.withEndpointRequest(serviceURL) {
            HolidayRequest(xmlns: namespace) {
                MyHoliday {
                    StartDate("2006-07-03")
                    EndDate("2006-07-07")
                }
                Me {
                    Number("42")
                    FirstName("Russ")
                    LastName("Miles")
                }
            }
        }

        then:
        def e = thrown(SoapFaultClientException)
        e.message == "Validation error"
    }

    /*
     * Test the custom request payload element configuration option
     *
     */
    def "test SOAP Document service with a custom payload element"() {
        when:
        def response = new XmlSlurper().parseText support.withEndpointRequest(serviceURL) {
            Vacation(xmlns: namespace) {
                Holiday {
                    StartDate("2006-07-03")
                    EndDate("2006-07-07")
                }
                Employee {
                    Number("42")
                    FirstName("Russ")
                    LastName("Miles")
                }
            }
        }

        then:
        def status = response.status
        status == "complete"
    }
}