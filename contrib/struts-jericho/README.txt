Jericho is a whiteboard proposal describing one possible implementation of Struts 2.x.

Since Struts 2.x is slated as a "revolution", the Apache practice is to assign a codename to a proposal until the Community comes to a consensus.

This proposal is called "Jericho" since it tries to tear-down the walls within the Struts architecture. Jericho proposes to open-up Struts by

* Declaring interfaces for all core components.

* Providing working base implementations for all core components.

* Encapsulating alll path references within "Location" objects (fka ActionForwards)and referring only to Locations from all other objects.

* Providing additional extension points from core components so that the "Inversion of Control" pattern is fully realized.

* Providing "POJO" signatures that encapsulate HTTP classes so that applications can be freed of HTTP semantics, if so desired.

* Retain optional access to HTTP objects so that applications can be free to do whatever they need to do.

-Backward Compatibility-

Jericho is a revolution and backward compatability with prior versions of Struts is not the prime consideration. However, care is being taken to create a clear migration path, where practible, so that Jericho is available to the widest community possible.

_DTD._ The Jericho Configuration file (DTD) builds on the best aspects of the Struts 1.2 DTD. The elements are different but still similar. Our goal is to allow a tool, such as a XLST processor, to migrate a Struts 1.2 DTD to Struts Jericho.

A second alternative to explore is to provide an alternate configuration loader that would map the Struts 1.2 elements to Struts Jericho objects at initialization.

_Base Classes._ New base classes for Struts 1.2.x ActionForms and Actions are to provided. These classes will provide the Struts 1.2.x behavior but also implement the Struts Jericho interfaces, so that the framework can use them interchangeably.

These same techniques may be applied to provide adaptors for other frameworks, so as to make Struts Jericho available to the widest community possible.

###