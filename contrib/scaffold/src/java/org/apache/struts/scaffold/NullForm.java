/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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

package org.apache.struts.scaffold;


import org.apache.struts.action.ActionForm;


/**
 * Empty (or "null") form for use with "formless" forms.
 * The Struts JSP tags require a form bean to create elements like
 * buttons, even if one is not actually needed.
 * The NullForm placates the tag by providing an empty form.
 */
public final class NullForm extends ActionForm {

    // blank form for use with command buttons

} // end NullForm