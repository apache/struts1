<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/layout/columnsLayout.jsp" flush="true">
  <comp:put name="numCols" value="2" />
  <comp:putList name="list0" >
    <comp:add value="/portal/login.jsp" />
    <comp:add value="/portal/messages.jsp" />
    <comp:add value="/portal/newsFeed.jsp" />
    <comp:add value="/portal/advert2.jsp" />
  </comp:putList>
  <comp:putList name="list1" >
    <comp:add value="/portal/advert3.jsp" />
    <comp:add value="/portal/stocks.jsp" />
    <comp:add value="/portal/whatsNew.jsp" />
    <comp:add value="/portal/personalLinks.jsp" />
    <comp:add value="/portal/search.jsp" />
  </comp:putList>
</comp:insert>