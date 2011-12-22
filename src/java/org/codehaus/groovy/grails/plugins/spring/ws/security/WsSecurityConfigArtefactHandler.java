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

package org.codehaus.groovy.grails.plugins.spring.ws.security;

import org.codehaus.groovy.grails.commons.ArtefactHandlerAdapter;
import static org.codehaus.groovy.grails.plugins.spring.ws.security.DefaultGrailsWsSecurityConfigClass.WS_SECURITY_CONFIG;

/**
 *
 * @author Tareq Abedrabbo (tareq.abedrabbo@gmail.com)
 */
public class WsSecurityConfigArtefactHandler extends ArtefactHandlerAdapter {

	public static String TYPE = "WsSecurityConfig";

    public WsSecurityConfigArtefactHandler(){
        super(TYPE, GrailsWsSecurityConfigClass.class, DefaultGrailsWsSecurityConfigClass.class, WS_SECURITY_CONFIG);
    }

}
