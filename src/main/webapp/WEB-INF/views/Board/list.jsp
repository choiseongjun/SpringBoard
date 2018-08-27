<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp" %>
<script>

$(function(){
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.do";
	});
});
function list(curPage){
//	location.href="${path}/board/list.do?curPage="+page;
	$.ajax({
		  method: "GET",
		  url: "${path}/board/ajaxList.do?curPage="+curPage,
		  success : function(data) {
			  
				$("#ajaxTest").empty();
				
				var str = '';
				data.list.forEach(function(e){
				str+='<tr id="ajaxRemove">';
				str+='<td>'+ e.bno +'</td>';
				str+='<td>';
				str+='<a href="${path}/board/view.do?bno=' + e.bno + '">';
				 
				for(var i = 1;i<=e.re_level;i++){
					str+='re: '
				}
				str+=e.title;
				if(e.cnt > 0){
					str+="<span style='color:red;'>("+e.cnt+")</span>";
				}		  
 
				str+='</a>';
				str+='</td>';
				str+='<td>'+e.name+'</td>';
				str+='<td>'+ format(new Date(e.regdate), 'yyyy-MM-dd hh:mm:ss') +'</td>';
				str+='<td>'+e.viewcnt+'</td>';
				str+='</tr>';
				  
			  });
			  $("#ajaxTest").append(str);
			  
		  },
		  fail : function(e) {
			  console.log(e);
		  }
	});
}


format = function date2str(x, y) {
    var z = {
        M: x.getMonth() + 1,
        d: x.getDate(),
        h: x.getHours(),
        m: x.getMinutes(),
        s: x.getSeconds()
    };
    y = y.replace(/(M+|d+|h+|m+|s+)/g, function(v) {
        return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
    });

    return y.replace(/(y+)/g, function(v) {
        return x.getFullYear().toString().slice(-v.length)
    });
}


var curpage = 1;
var totalBoardCount = ${map.count}; //현재 게시글 총 개수
/* var pageSize = 10; //페이지블럭개수  */
var totalPage = Math.ceil(totalBoardCount/10);
//전체페이지수 
  

pageSize=Math.ceil(curpage/totalBoardCount);
var p = 1;//다음에 뿌릴 페이지 시작

function changePageClickEvent(){
	$('.pageNumber').on('click',function(){//페이지 버튼 누를 시 이벤트
		$('.pageNumber').css('color','white');//페이지 흰색으로 초기화
		$(this).css('color','red');//선택한 페이지 번호 빨간색으로 바꿔주기.
		
		var pageNumber = $(this).attr('data-pageNumber');//선택한 페이지 번호 가져오기
		list(pageNumber);//리스트 호출
	});
	
}
function makeBeforePage(){
	p = p-10;
	
	var str = "";
	str += "<td colspan='5' align='center'>";
	
	
	if(p!=1){
		str += "<a onclick='makeBeforePage()'>이전</a>";
	}
	for(var i = p;i<=p+9;i++){
		str += "<a class='pageNumber' data-pageNumber='"+i+"'>"+i+"</a>";
	}
	str += "<a onclick='makeNextPage()'>다음</a>";
	str += "</td>";
	
	$("#pageBlock").html(str);
	
	changePageClickEvent();//페이징 클릭이벤트 걸어주기
	
	
	$('.pageNumber').eq(0).click();
	
}

function makeNextPage(){
	p = p+10;
	
	var str = "";
	str += "<td colspan='5' align='center'>";
	str += "<a onclick='makeBeforePage()'>이전</a>";
	
	if(totalPage>=p+10){//< 11 12 13 14 15 16 17 18 19 20 >
		for(var i = p;i<=p+9;i++){
			str += "<a class='pageNumber' data-pageNumber='"+i+"'>"+i+"</a>";
		}
		str += "<a onclick='makeNextPage()'>다음</a>";
		
	}else{
		for(var i = p;i<=totalPage;i++){//< 11 12 13 14 15 16 17
			str += "<a class='pageNumber' data-pageNumber='"+i+"'>"+i+"</a>";
		}
	}
	str += "</td>";
	
	$("#pageBlock").html(str);
	
	changePageClickEvent();//페이징 클릭이벤트 걸어주기
	
	$('.pageNumber').eq(0).click();
	
}

$(function(){
	
	list(1);
	
	var str = "";
	str += "<td colspan='5' align='center'>";
	if(totalPage>10){
		for(var i = 1;i<=10;i++){
			str += "<a class='pageNumber' data-pageNumber='"+i+"'>"+i+"</a>";
		}
	}else{
		for(var i = 1;i<=totalPage;i++){
			str += "<a class='pageNumber' data-pageNumber='"+i+"'>"+i+"</a>";
		}
	}
	str += "<a onclick='makeNextPage()'>다음</a>";
	str += "</td>";
	
	$("#pageBlock").html(str);
	
	changePageClickEvent();//페이징 클릭이벤트 걸어주기
	
	$('#pageBlock td a').eq(0).css('color','red');//처음 페이지1 글자색 빨간색 초기화
	
});
</script>

<style>
#pageBlock td a{
	margin:0 0 0 10px;
	cursor:pointer;
}
</style>
</head>
<body>
<h2 color="red" align="middle">게시판</h2>

<!-- 검색폼 -->
<form name="form1" method="post"
	action="${path}/board/list.do">
	<select name="search_option">
		<option value="name"
<c:if test="${map.search_option == 'name'}">selected</c:if>
		>이름</option>
		<option value="title" 
<c:if test="${map.search_option == 'title'}">selected</c:if>
		>제목</option>
		<option value="content" 
<c:if test="${map.search_option == 'content'}">selected</c:if>
		>내용</option>
		<option value="all" 
<c:if test="${map.search_option == 'all'}">selected</c:if>
		>이름+내용+제목</option>
	</select>
	<input name="keyword" value="${map.keyword}">
	<input type="submit" value="조회">
</form>


 <table class="table table-dark">
 	<thead>
	 	<tr>
			<th>번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
 	</thead>
 	<tbody id="ajaxTest">
		<!-- forEach var="개별데이터" items="집합데이터" -->
		<%-- <c:forEach var="row" items="${map.list}">
		 	<tr> 
				<td>${row.bno}</td>
				<td>
					<a href="${path}/board/view.do?bno=${row.bno}">
					 <c:forEach var="a" begin="1" end="${row.re_level}" step="1">
					re: 
					</c:forEach>
					${row.title}
					</a>
					<c:if test="${row.cnt > 0}">
						<span style="color:red;">( ${row.cnt} )</span>
					</c:if>   
				</td>
				<td>${row.name}</td>
				<td><fmt:formatDate value="${row.regdate}"
					pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>${row.viewcnt}</td> 
			</tr>
		 </c:forEach> --%>
 	</tbody>

<!-- 페이지 네비게이션 출력 -->
	<tfoot>
		<tr id = "pageBlock">
			<%-- <td colspan="5" align="center">
				<c:if test="${map.pager.curBlock > 1}">
					<a onclick="list('1')">[처음]</a>
				</c:if>
				<c:if test="${map.pager.curBlock > 1}">
					<a onclick="list('${map.pager.prevPage}')">
					[이전]</a>
				</c:if>
				<c:forEach var="num" 
					begin="${map.pager.blockBegin}"
					end="${map.pager.blockEnd}">
					<c:choose>
						<c:when test="${num == map.pager.curPage}">
						<!-- 현재 페이지인 경우 하이퍼링크 제거 -->
							<span style="color:red;">${num}</span>
						</c:when>
						<c:otherwise>
							<a onclick="list(${num})">${num}</a>
						</c:otherwise>
						
					</c:choose>
				</c:forEach>
				<c:if test="${map.pager.curBlock < map.pager.totBlock}">
					<a 
					onclick="list('${map.pager.nextPage}')">[다음]</a>
				</c:if>
				<c:if test="${map.pager.curPage < map.pager.totPage}">
					<a 
					onclick="list('${map.pager.totPage}')">[끝]</a>
				</c:if>
			</td> --%>
		</tr>
	</tfoot>

</table>
<button type="button" id="btnWrite" align="right">글쓰기</button>
<table class="table table-dark"><th>${map.count}개의 게시물이 있습니다.</th></table> 

</body>
</html>



















