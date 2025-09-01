<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>글 목록</title>
</head>
<body>
    <center>
        <h1>글 목록</h1>
        <h3>테스트님 환영합니다...<a href="logout.do">Log-out</a></h3>

        <form action="getBoardList.jsp" method="post">
            <table border="1" cellpadding="0" cellspacing="0" width="700">
                <tr align="right">
                    <select name="searchCondition">
                        <option value="TITLE">제목</option>
                        <option value="CONTENT">내용</option>
                    </select>

                    <input name="searchKeyword" type="text" />
                    <input type="submit" value="검색" />
                </tr>
            </table>
        </form>
        <%-- 검색 종료 --%>

        <table border="1" cellpadding="0" cellspacing="0" width="700">
            <tr>
                <th bgcolor="orange" width="100">번호</th>
                <th bgcolor="orange" width="200">제목</th>
                <th bgcolor="orange" width="150">작성자</th>
                <th bgcolor="orange" width="150">등록일</th>
                <th bgcolor="orange" width="100">조회수</th>
            </tr>


            <c:forEach var="board" items="${boardList}" >
                <tr>
                    <td>${board.seq}</td>
                    <td align="left">
                        <a href="getBoard.do?seq=${board.seq}">
                            ${board.title}
                        </a>
                    </td>
                    <td>${board.writer}</td>
                    <td>${board.regdate}</td>
                    <td>${board.cnt}</td>
                </tr>
            </c:forEach>

        </table>

        <br>

        <a href="insertBoard.jsp">새글 등록</a>

    </center>
</body>
</html>
