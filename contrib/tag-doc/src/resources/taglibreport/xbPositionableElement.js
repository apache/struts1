/*
 * xbPositionableElement.js
 * $Revision: 1.1 $ $Date: 2003/04/01 04:29:17 $
*/
/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Netscape Cross Browser Positionable Element code.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 2002
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s): Doron Rosenberg <doron@netscape.com>
 *                 Bob Clary <bclary@netscape.com>
 *                 
 *
 * ***** END LICENSE BLOCK ***** */

/*
 * xbPositionableElement - 
 * use DHMTL to position an element displaces (offsetX, offsetY)
 * from a geometric position in the viewport.
 * 
 */

function xbPositionableElement(id, sideX, sideY, offsetX, offsetY)
{
  this.id              = id;
  this.name            = 'xbPositionableElement_' + (++xbPositionableElement._name);
  this.runId           = null;  
  this.refreshInterval = 100;
  this.div             = null;
  
  if (typeof(sideX) != 'string')
  {
    sideX = 'left'
  }
  else
  {
    sideX = sideX.toLowerCase();
  }

  if (typeof(sideY) != 'string')
  {
    sideY = 'top'
  }
  else
  {
    sideY = sideY.toLowerCase();
  }

  if (typeof(offsetX) != 'number')
  {
    offsetX = 0;
  }

  if (typeof(offsetY) != 'number')
  {
    offsetY = 0;
  }

  this.sideX   = sideX;
  this.sideY   = sideY;
  this.offsetX = offsetX;
  this.offsetY = offsetY;

  window[this.name] = this;
}  

xbPositionableElement._name = -1;

xbPositionableElement.prototype.start = function ()
{
  this.stop();
  
  // since start() should be called after the document has loaded, we can finally assign element
  if (!this.div)
  {
    if (document.getElementById)
    {
      this.div = document.getElementById(this.id);
      this.styleObj = this.div.style;	
    }
    else if (document.layers)
    {
      this.div = document.layers[this.id];
      this.styleObj = this.div;
    }
    else if (document.all)
    {
      this.div = document.all[this.id];
      this.styleObj = this.div.style;	
    }

    this.width = this._getInnerSize("width");
  }	
  
  this._updatePosition();
  this.runId = setTimeout(this.name + '.start()', this.refreshInterval);    
};

xbPositionableElement.prototype.stop = function ()
{
  if (this.runId)
    clearTimeout(this.runId);
    
  this.runId = null;
};

xbPositionableElement.prototype._getInnerSize = function (propName)
{
  var val = 0;

  if (document.layers)
  {
    // navigator 4
    val = this.div.document[propName];
  }
  else if (typeof(this.div.style[propName]) == 'number')
  {
    // opera
    // bug in Opera 6 width/offsetWidth. Use clientWidth
    if (propName == 'width' && typeof(this.div.clientWidth) == 'number')
      val = this.div.clientWidth;
    else
      val =  this.styleObj[propName];
  }
  else
  {
    //mozilla and IE
    switch (propName)
    {
    case 'height':
       if (typeof(this.div.offsetHeight) == 'number')
         val =  this.div.offsetHeight;
       if (val == 0)
         val =  this.height;
       break;
 
    case 'width':
       if (typeof(this.div.offsetWidth) == 'number')
         val = this.div.offsetWidth;
       if (val == 0)
         val =  this.width;
       break;
    }
  }
  return val;
};

xbPositionableElement.prototype._getScrollOffset = function (propName)
{
  var rv = 0;

  if (document.body && typeof(document.body.scrollTop) == 'number')
  {
    rv = document.body[propName=='top'?'scrollTop':'scrollLeft'];
  }
  else if (typeof(window.pageYOffset) == 'number')
  {
    rv = window[propName=='top'?'pageYOffset':'pageXOffset'];  
  }

  return rv;
};

xbPositionableElement.prototype._updatePosition = function ()
{
  var windowHeight;
  var windowWidth;
  var x = 0;
  var y = 0;

  if (document.body && typeof(document.body.clientHeight) == 'number')
  {
    windowHeight = document.body.clientHeight;
    windowWidth  = document.body.clientWidth;
  }
  else if (typeof(window.innerHeight) == 'number')
  {
    windowHeight = window.innerHeight - 16;
    windowWidth  = window.innerWidth  - 16;  
  }

  // calculate the positions of the view port position
  switch (this.sideX)
  {
    case 'left':
      x = this._getScrollOffset('left');
      break;

    case 'center':
      x = (windowWidth / 2) - (this._getInnerSize('width')/2) + this._getScrollOffset('left');
      break;
	  
    case 'right':
      x = windowWidth - this._getInnerSize('width') + this._getScrollOffset('left');
      break;
  }

  switch (this.sideY)
  {
    case 'top':
      y = this._getScrollOffset('top');
      break;

    case 'center':
      y = (windowHeight / 2) - (this._getInnerSize('height') / 2) + this._getScrollOffset('top');
      break; 
	  
    case 'bottom':
      y = windowHeight - this._getInnerSize('height') + this._getScrollOffset('top');
      break;
  }
  
  if (typeof(this.styleObj.left) == 'number')
  {
    this.styleObj.left = (x + this.offsetX);
    this.styleObj.top  = (y + this.offsetY);
  }
  else
  {
    this.styleObj.left = (x + this.offsetX) + 'px';
    this.styleObj.top  = (y + this.offsetY) + 'px';
  }
};

