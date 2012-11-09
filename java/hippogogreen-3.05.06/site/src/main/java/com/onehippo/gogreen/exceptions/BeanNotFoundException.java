/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.exceptions;

import org.hippoecm.hst.core.component.HstComponentException;

/**
 * This exception can be thrown when, for example, a hippo bean cannot be found. 
 * The PageErrorHandler can react upon this exception.
 */
public class BeanNotFoundException extends HstComponentException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BeanNotFoundException exception with the given message. 
     *
     * @param   message
     *          the exception message
     */
    public BeanNotFoundException(String message) {
        super(message);
    }

}