<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/layout/columnsLayout.jsp" flush="true">
  <comp:put name="numCols" value="2" />
  <comp:putList name="list0" >
    <comp:add value="/tutorial/portal/login.jsp" />
    <comp:add value="/tutorial/portal/messages.jsp" />
    <comp:add value="/tutorial/portal/newsFeed.jsp" />
    <comp:add value="/tutorial/portal/advert2.jsp" />
  </comp:putList>
  <comp:putList name="list1" >
    <comp:add value="/tutorial/portal/advert3.jsp" />
    <comp:add value="/tutorial/portal/stocks.jsp" />
    <comp:add value="/tutorial/portal/whatsNew.jsp" />
    <comp:add value="/tutorial/portal/personalLinks.jsp" />
    <comp:add value="/tutorial/portal/search.jsp" />
  </comp:putList>
</comp:insert>