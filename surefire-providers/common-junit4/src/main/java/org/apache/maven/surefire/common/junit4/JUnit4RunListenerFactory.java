package org.apache.maven.surefire.common.junit4;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.surefire.util.ReflectionUtils;
import org.junit.runner.notification.RunListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Kristian Rosenvold
 */
public class JUnit4RunListenerFactory
{
    public static List<RunListener> createCustomListeners( String listenerProperty )
    {
        List<RunListener> result = new LinkedList<RunListener>();
        if ( listenerProperty == null )
        {
            return result;
        }

        for ( String thisListenerName : listenerProperty.split( "," ) )
        {
            RunListener customRunListener =
                (RunListener) ReflectionUtils.instantiate( Thread.currentThread().getContextClassLoader(),
                                                           thisListenerName );
            result.add( customRunListener );
        }

        return result;
    }

}
