header {
/*
 *  The Apache Software License, Version 1.1
 *
 *  Copyright (c) 1999 The Apache Software Foundation.  All rights
 *  reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  3. The end-user documentation included with the redistribution, if
 *  any, must include the following acknowlegement:
 *  "This product includes software developed by the
 *  Apache Software Foundation (http://www.apache.org/)."
 *  Alternately, this acknowlegement may appear in the software itself,
 *  if and wherever such third-party acknowlegements normally appear.
 *
 *  4. The names "The Jakarta Project", "Struts", and "Apache Software
 *  Foundation" must not be used to endorse or promote products derived
 *  from this software without prior written permission. For written
 *  permission, please contact apache@apache.org.
 *
 *  5. Products derived from this software may not be called "Apache"
 *  nor may "Apache" appear in their names without prior written
 *  permission of the Apache Group.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 *  ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 *  USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *  SUCH DAMAGE.
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many
 *  individuals on behalf of the Apache Software Foundation.  For more
 *  information on the Apache Software Foundation, please see
 *  <http://www.apache.org/>.
 */

package org.apache.struts.validator.validwhen;

import java.util.Stack; 
import org.apache.commons.validator.ValidatorUtil;

}
class ValidWhenParser extends Parser;
options {
k=6;
defaultErrorHandler=false;
}
{Stack argStack = new Stack();
Object form;
int index;
String value;

    public void setForm(Object f) { form = f; };
    public void setIndex (int i) { index = i; };
    public void setValue (String v) { value = v; };

    public boolean getResult() {
       return ((Boolean)argStack.peek()).booleanValue();
    }

    private final int LESS_EQUAL=0;
    private final int LESS_THAN=1;
    private final int EQUAL=2;
    private final int GREATER_THAN=3;
    private final int GREATER_EQUAL=4;
    private final int NOT_EQUAL=5;
    private final int AND=6;
    private final int OR=7;

    private  boolean evaluateComparison (Object v1, Object compare, Object v2) {
        boolean intCompare = true;
	if ((v1 == null) || (v2 == null)) {
		if (String.class.isInstance(v1)) {
			if (((String) v1).length() == 0) {
				v1 = null;
			}
		}
		if (String.class.isInstance(v2)) {
			if (((String) v2).length() == 0) {
				v2 = null;
			}
		}
		switch (((Integer)compare).intValue()) {
		case LESS_EQUAL:
		case GREATER_THAN:
		case LESS_THAN:
		case GREATER_EQUAL:
			return false;
		case EQUAL:
		    return (v1 == v2);
		case NOT_EQUAL:
		    return (v1 != v2);
		}
	}
        if (!Integer.class.isInstance(v1) &&
	    !Integer.class.isInstance(v2)) {
	    intCompare = false;
	}
	if (intCompare) {
	    try {
		int v1i = 0, v2i = 0;
		if (Integer.class.isInstance(v1)) {
		    v1i = ((Integer)v1).intValue();
		} else {
		    v1i = Integer.parseInt((String) v1);
		}
		if (Integer.class.isInstance(v2)) {
		    v2i = ((Integer)v2).intValue();
		} else {
		    v2i = Integer.parseInt((String) v2);
		}
		switch (((Integer)compare).intValue()) {
		case LESS_EQUAL:
		    return (v1i <= v2i);

		case LESS_THAN:
		    return (v1i < v2i);

		case EQUAL:
		    return (v1i == v2i);

		case GREATER_THAN:
		    return (v1i > v2i);

		case GREATER_EQUAL:
		    return (v1i >= v2i);

		case NOT_EQUAL:
		    return (v1i != v2i);
		}
	    } catch (NumberFormatException ex) {};
	}
	String v1s = "", v2s = "";

	if (Integer.class.isInstance(v1)) {
	    v1s = ((Integer)v1).toString();
	} else {
	    v1s = (String) v1;
	}

	if (Integer.class.isInstance(v2)) {
	    v2s = ((Integer)v2).toString();
	} else {
	    v2s = (String) v2;
	}

	int res = v1s.compareTo(v2s);
	switch (((Integer)compare).intValue()) {
	case LESS_EQUAL:
	    return (res <= 0);

	case LESS_THAN:
	    return (res < 0);

	case EQUAL:
	    return (res == 0);

	case GREATER_THAN:
	    return (res > 0);

	case GREATER_EQUAL:
	    return (res >= 0);

	case NOT_EQUAL:
	    return (res != 0);
	}
	return true;
    }

}


integer
: d:DECIMAL_LITERAL { argStack.push(Integer.valueOf(d.getText())); }
| h:HEX_LITERAL { argStack.push(Integer.valueOf(d.getText())); }
| o:OCTAL_LITERAL { argStack.push(Integer.valueOf(d.getText())); } ;

string : str:STRING_LITERAL { argStack.push(str.getText().substring(1, str.getText().length()-1)); };

identifier 
: str:IDENTIFIER { argStack.push(str.getText()); } ;

field 
: identifier LBRACKET RBRACKET identifier {
            Object i2 = argStack.pop();
            Object i1 = argStack.pop();
            argStack.push(ValidatorUtil.getValueAsString(form, i1 + "[" + index + "]" + i2));
}
| identifier LBRACKET integer RBRACKET identifier {
            Object i5 = argStack.pop();
            Object i4 = argStack.pop();
            Object i3 = argStack.pop();
            argStack.push(ValidatorUtil.getValueAsString(form, i3 + "[" + i4 + "]" + i5));
}
| identifier LBRACKET integer RBRACKET LBRACKET {
            Object i7 = argStack.pop();
            Object i6 = argStack.pop();
            argStack.push(ValidatorUtil.getValueAsString(form, i6 + "[" + i7 + "]"));
} 
| identifier LBRACKET RBRACKET {
            Object i8 = argStack.pop();
            argStack.push(ValidatorUtil.getValueAsString(form, i8 + "[" + index + "]"));
}
| identifier  {
            Object i9 = argStack.pop();
            argStack.push(ValidatorUtil.getValueAsString(form, (String)i9));
}
;

literal : integer | string | "null" { argStack.push(null);} | THIS {argStack.push(value);};

value : field | literal ;

expression : expr EOF;

expr: LPAREN comparisonExpression RPAREN | LPAREN joinedExpression RPAREN;

joinedExpression : expr join expr {
   Boolean v1 = (Boolean) argStack.pop();
   Integer join = (Integer) argStack.pop();
   Boolean v2 = (Boolean) argStack.pop();
   if (join.intValue() == AND) {
      argStack.push(new Boolean(v1.booleanValue() && v2.booleanValue()));
} else {
      argStack.push(new Boolean(v1.booleanValue() || v2.booleanValue()));
     }
};

join : ANDSIGN { argStack.push(new Integer(AND)); } | 
        ORSIGN { argStack.push(new Integer(OR)); };

comparison : 
   EQUALSIGN  { argStack.push(new Integer(EQUAL)); } |
   GREATERTHANSIGN { argStack.push(new Integer(GREATER_THAN)); } |
   GREATEREQUALSIGN  { argStack.push(new Integer(GREATER_EQUAL)); } |
   LESSTHANSIGN  { argStack.push(new Integer(LESS_THAN)); } |
   LESSEQUALSIGN  { argStack.push(new Integer(LESS_EQUAL)); } |
   NOTEQUALSIGN { argStack.push(new Integer(NOT_EQUAL)); } ;

comparisonExpression : value comparison value {
	    Object v2 = argStack.pop();
	    Object comp = argStack.pop();
        Object v1 = argStack.pop();
        argStack.push(new Boolean(evaluateComparison(v1, comp, v2)));
};


class ValidWhenLexer extends Lexer;

options {
 k=2;
caseSensitive=false;
defaultErrorHandler=false;
}
tokens {
ANDSIGN="and";
ORSIGN="or";
}

WS : ( ' ' | '\t' | '\n' | '\r' )+
     { $setType(Token.SKIP); }
   ;

DECIMAL_LITERAL : ('1'..'9') ('0'..'9')* ;

HEX_LITERAL : '0' 'x'  ('0'..'9' | 'a'..'f')+ ;

OCTAL_LITERAL : '0' ('0'..'7')+ ;

STRING_LITERAL : ('\'' (~'\'')+ '\'') | ('\"' (~'\"')+ '\"') ;

LBRACKET : '[' ;

RBRACKET : ']' ;

LPAREN : '(' ;

RPAREN : ')' ;

THIS : "*this*" ;

IDENTIFIER : ( 'a'..'z' | '.') ( 'a'..'z' | '0'..'9' | '.')+ ;

EQUALSIGN : '=' '=' ;

NOTEQUALSIGN : '!' '=' ;

LESSTHANSIGN : '<';

GREATERTHANSIGN : '>';

LESSEQUALSIGN : '<' '=';

GREATEREQUALSIGN : '>' '=';

