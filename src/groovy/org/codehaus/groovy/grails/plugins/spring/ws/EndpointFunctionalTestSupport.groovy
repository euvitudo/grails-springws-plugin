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

package org.codehaus.groovy.grails.plugins.spring.ws

import org.springframework.ws.client.core.WebServiceTemplate
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import groovy.xml.MarkupBuilder

import org.codehaus.groovy.grails.plugins.spring.ws.security.WsSecurityConfigFactory

/**
 * Convenience endpoint functional test base class.
 *
 * @author Russ Miles (russ@russmiles.com)
 * @author Tareq Abedrabbo (tareq.abedrabbo@gmail.com)
 *
 */
public class EndpointFunctionalTestSupport {

    def consoleOutput


    def withEndpointRequest = {url, payload ->
        def webServiceTemplate = new WebServiceTemplate()
        def writer = new StringWriter()
        def request = new MarkupBuilder(writer)
        payload.delegate = request
        payload.call()

        return EndpointFunctionalTestSupport.sendToEndpoint(webServiceTemplate, url, writer.toString())
    }

    // accepts a security config instance and applies the resulting security interceptor
    def withSecuredEndpointRequest = {url, wsSecurityConfig, payload ->
        def webServiceTemplate = new WebServiceTemplate()
        def writer = new StringWriter()
        def request = new MarkupBuilder(writer)
        payload.delegate = request
        payload.call()

        // set the security Interceptor
        def securityInterceptor = WsSecurityConfigFactory.createInterceptor(securityConfigClass: wsSecurityConfig)
        //def swsTemplate =  webServiceTemplate.webServiceTemplate
        def swsTemplate =  webServiceTemplate
        swsTemplate.interceptors = [securityInterceptor]

        def response = EndpointFunctionalTestSupport.sendToEndpoint(webServiceTemplate, url, writer.toString())

        // clean up
        swsTemplate.interceptors = [securityInterceptor]

        return response
    }

    static def sendToEndpoint(wst, url, request) {
        StringResult result = new StringResult()
        StringSource source = new StringSource(request as String)
        wst.setDefaultUri(url)
        wst.sendSourceAndReceiveToResult(source, result)
        return result.toString()
    }

}
