Jericho is a whiteboard proposal describing one possible implementation of Struts 2.x.

Since Struts 2.x is slated as a "revolution", the Apache practice is to assign a codename to a proposal until the Community comes to a consensus.

This proposal is called "Jericho" since it tries to tear-down the walls within the Struts architecture. Jericho proposes to open-up Struts by

* Declaring interfaces for all core components.

* Providing working base implementations for all core components.

* Encapsulating all path references within "Location" objects (aka ActionForwards)and referring only to Locations from all other objects.

* Providing additional extension points from core components so that the "Inversion of Control" pattern is fully realized. (e.g., a populate method for the FormHandler.)

* Providing "POJO" signatures that encapsulate servlet/portlet/soap behavior so that applications can be freed of servlet/portlet/soap semantics, if so desired. This strategy would also be applied to optional packages like Validator and Tiles.

* Retain optional access to servlet/portlet objects so that applications can be free to do whatever they need to do.

-Backward Compatibility-

Jericho is a revolution and backward compatibility with prior versions of Struts is not the prime consideration. However, care is being taken to create a clear migration path, where practical, so that Jericho is available to the widest community possible.

-Taglibs/Tools-

JSP taglibs and other presentation tools will be packaged separately from the core controller classes. Tags and tools can access the framework through a StrutsContext object passed through the request, rather than searching through the servlet or portlet contexts.

-ActionForms/Actions, et al-

New implementations of Struts 1.x base classes will be provided. These classes will provide the Struts 1.x behavior, but will also implement the Jericho interfaces. (Much the same way perform was supported in Struts 1.1.)

These same techniques may be applied to provide adaptors for other frameworks, so as to make Struts Jericho available to the widest community possible.

-Configuration-

The Jericho Configuration builds on the best aspects of Struts 1.2. The elements are different but still similar. Our goal is to allow a tool, such as a XLST processor, to migrate a Struts 1.2 DTD to Struts Jericho.

A second alternative might be to provide an alternate configuration loader that would map the Struts 1.2 elements to Struts Jericho objects at initialization.

-Servlet/Portlet/Soap/Mock support-

Core components will implement signatures that reference a StrutsContext. Implementations of this interface can be backed by servlet, portlet, soap, or mock implementations as needed.

-Also under consideration-

* Services framework. Utilize Spring IoC, HiveMind, Avalon, Pico, Cornerstone, et cetera, to configure services.

* Configuration testing. Integrate utility of Struts TestCase, ideally configured from an XML configuration (a la Canoo Webtest).

* Replace DTD with XML Schema.

* Utilize protocols in resource paths. An mapped path would look like "mapping://foo" or a tiles forward would be "tiles://tilesdef"  This would make it easy to plug in handlers to support different presentation engines.  If no protocol is specified, the path is handled as usual.

* Combine form-bean and validator-form configurations. A single element would allow everything Struts knows about a form to be configured in one place.

* Integrate Tiles definitions into core configuration.

* Adopt Struts Workflow and Struts Menu as standard options, like Validator and Tiles.

* Utilize JodaBeans <http://www.joda.org/> as a foundation class for core components.

* Load all configuration defaults form a core.properties file so that less is hardcoded, including a PlugIn class to bootstrap loading the core configuration.

* Ensure that all framework objects in 2.0 have consistent factory-based mechanisms to instantiate instances. <http://nagoya.apache.org/bugzilla/show_bug.cgi?id=15604>

###