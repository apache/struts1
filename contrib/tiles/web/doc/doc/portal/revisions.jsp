<table  width="100%">
<tr>
<th bgcolor="aqua"><FONT size=4>History</FONT></th></tr>
  <TR>
    <TD><FONT size=2><STRONG> 02&nbsp;nov. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  Correct another bug with Orion : "importAttribute 
        not working whenreused"</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 29&nbsp;oct. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  
            Allow body 
        content in tag  
            &lt;put&gt; and &lt;add&gt; in 
        xml files</FONT>
        <LI><FONT size=2>Correct bug with Orion : "insert tag attribute not 
        reset when ignore=true".</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 08&nbsp;oct. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  
            Correct bug&nbsp;"path overloaded when 
        inheriting definition".</FONT>
        <LI><FONT size=2> Add 
        example "dynamic portal"</FONT>
        <LI><FONT size=2>Add Tile Rss Channel (Thanks Ted 
    Husted)</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 27&nbsp;sept. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  
        Correct  
        bug&nbsp;preventing definitions file to be loaded when using 
        latest commons-digester.jar.</FONT>
        <LI><FONT size=2>Remove component-config.tld 
        (replaced by tiles-config.tld).</FONT>
        <LI><FONT size=2>Add a tilesForStruts1.0.jar file, intended for 
        Struts1.0</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 13 sept. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>Correct bug&nbsp;in 
        getAsString.&nbsp;Nothing is written when ignore=true and no attribute 
        is found.</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 09 sept. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>   Modify 
        ComponentActionServlet. It now  allows 
        to :</FONT>
		<ul>
        <LI><FONT size=2>  use a Struts 
        action as  the page 
        attribute of an  &lt;insert&gt; 
        or &lt;definition&gt;</FONT>
        <LI><FONT size=2>modify tile attributes inside 
        action</FONT>
        <LI><FONT size=2>associate an action with a tile, 
        and this for each tile.</FONT></LI>
		</ul>
        <LI><FONT size=2>Update and modify the "invoice" 
        example. </FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 30 jul. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>   Merge&nbsp;Documentation 
        and tutorial in  one 
        war file.</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 25 jul. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  Now use 
        commons.Digester, according to  new 
        Struts policy.</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 19 jul. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  Components 
        become Tiles.</FONT>
        <LI><FONT size=2>Start 
        integration in Struts</FONT>
        <LI><FONT size=2>Change code 
        package names accordingly</FONT></LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 04 jul. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>  Add flush 
        attribute to get</FONT>
        <LI><FONT size=2>Update 
        tutorial</FONT>
        <LI><FONT size=2>Add a DTD for 
        configuration file. However, DTD is not used (yet) for 
        validation.</FONT> </LI></UL></TD></TR> 
  <TR>
    <TD><FONT size=2><STRONG> 02 jul. 2001</STRONG> </FONT></TD></TR>
  <TR>
    <TD>
      <UL>
        <LI><FONT size=2>Tested 
        successfully on Tomcat-3.2.1, Tomcat-4.0b5, Orion-1.4.5, 
        Resin-2.0.0</FONT>
        <LI><FONT size=2>Correct the 
        release() bug preventing framework to run on Resin-2.0.0</FONT>
		  <UL>
          <LI><FONT size=2>Remove calls 
          to release() in doEndTag(), call to releaseInternal() instead.</FONT> 
          </LI></UL></LI></UL></TD></TR>
  <TR>
    <TD><FONT size=2><STRONG>&nbsp;&nbsp; <A 
      href="doc/revisionsCont.html"><FONT 
      size=2><STRONG>more 
...</STRONG></FONT></A></STRONG></FONT></TD></TR></table>