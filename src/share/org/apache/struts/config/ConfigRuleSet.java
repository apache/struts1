/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/config/ConfigRuleSet.java,v 1.14 2002/12/21 04:42:20 martinc Exp $
 * $Revision: 1.14 $
 * $Date: 2002/12/21 04:42:20 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.config;


import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.apache.commons.digester.RuleSetBase;
import org.apache.struts.util.RequestUtils;
import org.xml.sax.Attributes;


/**
 * <p>The set of Digester rules required to parse a Struts
 * configuration file (<code>struts-config.xml</code>).</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.14 $ $Date: 2002/12/21 04:42:20 $
 * @since Struts 1.1
 */

public class ConfigRuleSet extends RuleSetBase {


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Add the set of Rule instances defined in this RuleSet to the
     * specified <code>Digester</code> instance, associating them with
     * our namespace URI (if any).  This method should only be called
     * by a Digester instance.  These rules assume that an instance of
     * <code>org.apache.struts.config.ModuleConfig</code> is pushed
     * onto the evaluation stack before parsing begins.</p>
     *
     * @param digester Digester instance to which the new Rule instances
     *  should be added.
     */
    public void addRuleInstances(Digester digester) {

        digester.addObjectCreate
            ("struts-config/data-sources/data-source",
             "org.apache.struts.config.DataSourceConfig",
             "className");
        digester.addSetProperties
            ("struts-config/data-sources/data-source");
        digester.addSetNext
            ("struts-config/data-sources/data-source",
             "addDataSourceConfig",
             "org.apache.struts.config.DataSourceConfig");

        digester.addRule
            ("struts-config/data-sources/data-source/set-property",
             new AddDataSourcePropertyRule());

        digester.addRule
            ("struts-config/action-mappings",
             new SetActionMappingClassRule());

        digester.addFactoryCreate
            ("struts-config/action-mappings/action",
             new ActionMappingFactory());
        digester.addSetProperties
            ("struts-config/action-mappings/action");
        digester.addSetNext
            ("struts-config/action-mappings/action",
             "addActionConfig",
             "org.apache.struts.config.ActionConfig");

        digester.addSetProperty
            ("struts-config/action-mappings/action/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/action-mappings/action/exception",
             "org.apache.struts.config.ExceptionConfig",
             "className");
        digester.addSetProperties
            ("struts-config/action-mappings/action/exception");
        digester.addSetNext
            ("struts-config/action-mappings/action/exception",
             "addExceptionConfig",
             "org.apache.struts.config.ExceptionConfig");

        digester.addSetProperty
            ("struts-config/action-mappings/action/exception/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/action-mappings/action/forward",
             //             "org.apache.struts.config.ForwardConfig",
             "org.apache.struts.action.ActionForward",
             "className");
        digester.addSetProperties
            ("struts-config/action-mappings/action/forward");
        digester.addSetNext
            ("struts-config/action-mappings/action/forward",
             "addForwardConfig",
             "org.apache.struts.config.ForwardConfig");

        digester.addSetProperty
            ("struts-config/action-mappings/action/forward/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/controller",
             "org.apache.struts.config.ControllerConfig",
             "className");
        digester.addSetProperties
            ("struts-config/controller");
        digester.addSetNext
            ("struts-config/controller",
             "setControllerConfig",
             "org.apache.struts.config.ControllerConfig");

        digester.addSetProperty
            ("struts-config/controller/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/form-beans/form-bean",
             //             "org.apache.struts.config.FormBeanConfig",
             "org.apache.struts.action.ActionFormBean",
             "className");
        digester.addSetProperties
            ("struts-config/form-beans/form-bean");
        digester.addSetNext
            ("struts-config/form-beans/form-bean",
             "addFormBeanConfig",
             "org.apache.struts.config.FormBeanConfig");

        digester.addObjectCreate
            ("struts-config/form-beans/form-bean/form-property",
             "org.apache.struts.config.FormPropertyConfig",
             "className");
        digester.addSetProperties
            ("struts-config/form-beans/form-bean/form-property");
        digester.addSetNext
            ("struts-config/form-beans/form-bean/form-property",
             "addFormPropertyConfig",
             "org.apache.struts.config.FormPropertyConfig");

        digester.addSetProperty
            ("struts-config/form-beans/form-bean/form-property/set-property",
             "property", "value");

        digester.addSetProperty
            ("struts-config/form-beans/form-bean/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/global-exceptions/exception",
             "org.apache.struts.config.ExceptionConfig",
             "className");
        digester.addSetProperties
            ("struts-config/global-exceptions/exception");
        digester.addSetNext
            ("struts-config/global-exceptions/exception",
             "addExceptionConfig",
             "org.apache.struts.config.ExceptionConfig");

        digester.addSetProperty
            ("struts-config/global-exceptions/exception/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/global-forwards/forward",
             //             "org.apache.struts.config.ForwardConfig",
             "org.apache.struts.action.ActionForward",
             "className");
        digester.addSetProperties
            ("struts-config/global-forwards/forward");
        digester.addSetNext
            ("struts-config/global-forwards/forward",
             "addForwardConfig",
             "org.apache.struts.config.ForwardConfig");

        digester.addSetProperty
            ("struts-config/global-forwards/forward/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/message-resources",
             "org.apache.struts.config.MessageResourcesConfig",
             "className");
        digester.addSetProperties
            ("struts-config/message-resources");
        digester.addSetNext
            ("struts-config/message-resources",
             "addMessageResourcesConfig",
             "org.apache.struts.config.MessageResourcesConfig");

        digester.addSetProperty
            ("struts-config/message-resources/set-property",
             "property", "value");

        digester.addObjectCreate
            ("struts-config/plug-in",
             "org.apache.struts.config.PlugInConfig");
        digester.addSetProperties
            ("struts-config/plug-in");
        digester.addSetNext
            ("struts-config/plug-in",
             "addPlugInConfig",
             "org.apache.struts.config.PlugInConfig");

        digester.addRule
            ("struts-config/plug-in/set-property",
             new PlugInSetPropertyRule());

    }

}


/**
 * Class that calls <code>addProperty()</code> for the top object
 * on the stack, which must be a
 * <code>org.apache.struts.config.DataSourceConfig</code>.
 */

final class AddDataSourcePropertyRule extends Rule {

    public AddDataSourcePropertyRule() {
        super();
    }

    public void begin(Attributes attributes) throws Exception {
        DataSourceConfig dsc = (DataSourceConfig) digester.peek();
        dsc.addProperty(attributes.getValue("property"),
                        attributes.getValue("value"));
    }

}


/**
 * Class that records the name and value of a configuration property to be
 * used in configuring a <code>PlugIn</code> instance when instantiated.
 */

final class PlugInSetPropertyRule extends Rule {

    public PlugInSetPropertyRule() {
        super();
    }

    public void begin(Attributes attributes) throws Exception {
        PlugInConfig plugInConfig = (PlugInConfig) digester.peek();
        plugInConfig.addProperty(attributes.getValue("property"),
                                 attributes.getValue("value"));
    }

}


/**
 * Class that sets the name of the class to use when creating action mapping
 * instances. The value is set on the object on the top of the stack, which
 * must be a <code>org.apache.struts.config.ModuleConfig</code>.
 */
final class SetActionMappingClassRule extends Rule {

    public SetActionMappingClassRule() {
        super();
    }

    public void begin(Attributes attributes) throws Exception {
        String className = attributes.getValue("type");
        if (className != null) {
            ModuleConfig mc = (ModuleConfig) digester.peek();
            mc.setActionMappingClass(className);
        }
    }

}


/**
 * An object creation factory which creates action mapping instances, taking
 * into account the default class name, which may have been specified on the
 * parent element and which is made available through the object on the top
 * of the stack, which must be a
 * <code>org.apache.struts.config.ModuleConfig</code>.
 */
final class ActionMappingFactory extends AbstractObjectCreationFactory {

    public Object createObject(Attributes attributes) {

        // Identify the name of the class to instantiate
        String className = attributes.getValue("className");
        if (className == null) {
            ModuleConfig mc = (ModuleConfig) digester.peek();
            className = mc.getActionMappingClass();
        }

        // Instantiate the new object and return it
        Object actionMapping = null;
        try {
            actionMapping =
                RequestUtils.applicationInstance(className);
        } catch (Exception e) {
            digester.getLogger().error(
                    "ActionMappingFactory.createObject: ", e);
        }

        return actionMapping;
    }

}
