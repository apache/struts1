
 Struts-EL extension
 ===================

Introduction
------------

This subproject is an extension of the Struts tag library.  Each JSP custom tag
in this library is a subclass of an associated tag in the Struts tag library.
One difference is that this tag library does not use "rtexprvalues", it uses
the expression evaluation engine in the Jakarta Taglibs implementation of the
JSP Standard Tag Library (version 1.0) to evaluate attribute values.

In addition, some of the Struts tags were not ported to this library, as it was
determined that their functionality was entirely supplied by the JSTL.  These
particular Struts tags, and the reason for their non-porting will be described
in the documentation for this library.

In order to fully understand the correct utilization of this library, you must
understand the use and operation of the Struts tag library, and the use and
operation of the JavaServer Pages Standard Tag Library (hereafter called the
"JSTL"), along with the expression language (sometimes called the "EL") used
for evaluating attribute values.

Tag Mapping
-----------

In implementing the Struts-EL library, every Struts tag that provides a feature
that is not covered by the JSTL (1.0) library is mapped into the Struts-EL
library.  This section reviews which Struts tags are NOT implemented in the
Struts-EL library, and which JSTL tags provide that feature.

Many of the non-porting decisions were based on the fact that the JSTL
expression language itself provides the same functionality.  In those cases, in
addition to a possible JSTL tag name, the symbol "EL" will be listed.

Bean Tag Library Tags NOT Implemented in Struts-EL
--------------------------------------------------

Struts Tag        JSTL Tag
----------        --------
cookie				c:set, EL
define	         c:set, EL
header				c:set, EL
include	         c:import
parameter         c:set, EL
write             c:out

Logic Tag Library Tags NOT Implemented in Struts-EL
--------------------------------------------------

Struts Tag        JSTL Tag
----------        --------
empty					c:if, c:when, EL
equal					c:if, c:when, EL
greaterEqual		c:if, c:when, EL
greaterThan			c:if, c:when, EL
lessEqual			c:if, c:when, EL
lessThan				c:if, c:when, EL
notEmpty				c:if, c:when, EL
notEqual				c:if, c:when, EL

(Note that the "iterate" tag was originally ported, even with the presence of
the "c:forEach" tag, as the "indexed tag" functionality was not supported when
using "c:forEach" instead of "logic:iterate".  This has since been rectified,
such that the "indexed tag" functionality checks for being contained in a
"c:forEach" tag, in addition to the "logic:iterate" tag.  However, the ported
"iterate" tag has not been removed from Struts-EL, for backward compatibility.)

Html Tag Library Tags NOT Implemented in Struts-EL
--------------------------------------------------

None (all of them were ported).

Attribute Mapping
-----------------

At this point of the implementation, there is only one change (to two similar
tags) to the set of attributes between the Struts tags, and the Struts-EL tags.
The "logic:match" and "logic:notMatch" tags have an additional attribute named
"expr", which can take any value, and will be used as the value to compare
against, in addition to the choices of "cookie", "header", "name"/"property",
and "parameter".

Usage Requirements
------------------

The Struts-EL tag library requires the use of the Struts tag library, and the
Java Server Pages Standard Tag Library.  It is not necessary for JSP pages
using the Struts-EL tag library to also use the Struts tags or the JSTL tags,
but the Struts and JSTL tag libraries need to be part of the application
utilizing the Struts-EL tag library.

This is because the Struts-EL tag classes are all subclasses of Struts tag
classes, and their implementation uses classes provided by the JSTL.
