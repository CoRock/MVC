<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="net.board.db.*" %>

<%
   List boardList=(List)request.getAttribute("boardlist");
   int listcount=((Integer)request.getAttribute("listcount")).intValue();
   int nowpage=((Integer)request.getAttribute("page")).intValue();
   int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
   int startpage=((Integer)request.getAttribute("startpage")).intValue();
   int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<html>
<head>
   <title>MVC 게시판</title>
</head>

<body>
<!-- 게시판 리스트 -->
<table width=50% border="0" cellpadding="0" cellspacing="0">
   <tr align="center" valign="middle">
      <td colspan="4">MVC 게시판</td>
      <td align=right>
         <font size=2>글 개수 : ${listcount }</font>
      </td>
   </tr>
   
   <tr align="center" valign="middle" bordercolor="#333333">
      <td style="font-family:Tahoma;font-size:8pt;" width="8%" height="26">
         <div align="center">번호</div>
      </td>
      <td style="font-family:Tahoma;font-size:8pt;" width="50%">
         <div align="center">제목</div>
      </td>
      <td style="font-family:Tahoma;font-size:8pt;" width="14%">
         <div align="center">작성자</div>
      </td>
      <td style="font-family:Tahoma;font-size:8pt;" width="17%">
         <div align="center">날짜</div>
      </td>
      <td style="font-family:Tahoma;font-size:8pt;" width="11%">
         <div align="center">조회수</div>
      </td>
   </tr>
    
   <%
   	// row의 사이즈만큼 반복해서 읽어 온다
      for(int i=0;i<boardList.size();i++){
         // BoardBean: 게시판 구조로 만들어진 클래스
    	 BoardBean bl=(BoardBean)boardList.get(i);
   %>
   <tr align="center" valign="middle" bordercolor="#333333"
      onmouseover="this.style.backgroundColor='F8F8F8'"
      onmouseout="this.style.backgroundColor=''">
      <td height="23" style="font-family:Tahoma;font-size:10pt;">
         <%=bl.getBOARD_NUM()%>
      </td>
      
      <td style="font-family:Tahoma;font-size:10pt;">
         <div align="left">
         <%if(bl.getBOARD_RE_LEV()!=0){ %>
            <%for(int a=0;a<=bl.getBOARD_RE_LEV()*2;a++){ %>
            &nbsp;
            <%} %>
            ▶
         <%}else{ %>
            ▶
         <%} %>
         <a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM()%>">
            <%=bl.getBOARD_SUBJECT()%>
         </a>
         </div>
      </td>
      
      <td style="font-family:Tahoma;font-size:10pt;">
         <div align="center"><%=bl.getBOARD_NAME() %></div>
      </td>
      <td style="font-family:Tahoma;font-size:10pt;">
         <div align="center"><%=bl.getBOARD_DATE() %></div>
      </td>   
      <td style="font-family:Tahoma;font-size:10pt;">
         <div align="center"><%=bl.getBOARD_READCOUNT() %></div>
      </td>
   </tr>
   <%} %>
   <tr align=center height=20>
      <td colspan=7 style=font-family:Tahoma;font-size:10pt;>
         <%if(nowpage<=1){ %>
         [이전]&nbsp;
         <%}else{ %>
         <a href="./BoardList.bo?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
         <%} %>
         
         <%for(int a=startpage;a<=endpage;a++){
            if(a==nowpage){%>
            [<%=a %>]
            <%}else{ %>
            <a href="./BoardList.bo?page=<%=a %>">[<%=a %>]</a>&nbsp;
            <%} %>
         <%} %>
         
         <%if(nowpage>=maxpage){ %>
         [다음]
         <%}else{ %>
         <a href="./BoardList.bo?page=<%=nowpage+1 %>">[다음]</a>
         <%} %>
      </td>
   </tr>
   <tr align="right">
      <td colspan="5">
            <a href="./BoardWrite.bo">[글쓰기]</a>
      </td>
   </tr>
</table>
</body>
</html>