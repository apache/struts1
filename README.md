# STRUTS

![build status](https://circleci.com/gh/kawasima/struts1-forever.png?style=shield&circle-token=8f99c0e6c923ca570acda8c3640446fdacad2a47)

This struts1's fork is for maintenance to fix the vulnerabilities.

## Requirements

Original Struts requires Java 1.4 or higher. But struts1-forever requires Java 1.5 or higher.
Because Commons-Beanutils 1.9.2 is used for preventing a dangerous population.

## Fixed vulnerabilities

- CVE-2014-0114
- CVE-2016-1181
- CVE-2016-1182

## Introduction (Original)

This subproject contains the source code for the "Struts" application support
package, consisting of the following major components:

- Controller servlet with action mapping technology, implementing the Model-
  View-Controller (MVC) design pattern for web applications commonly called
  "Model 2", with a servlet as the "front component".

- Comprehensive custom tag library for building internationalized JSP pages
  that have HTML forms which interact with JavaBeans that are managed
  automatically by the controller servlet.

For help with installing and using Struts, see the Struts User Guide.

