<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 등록</title>
</head>
<body>
    <h1>글 등록</h1>
    <a href="/logout.do">Log-out</a>
    <hr>
    <form action="insertBoard.do" method="post" enctype="multipart/form-data">
        <table border="1" cellpadding="0" cellspacing="0" width="700">
            <tr>
                <td bgcolor="orange" width="70">제목</td>
                <td align="left">
                    <input type="text" name="title" />
                </td>
            </tr>
            <tr>
                <td bgcolor="orange">작성자</td>
                <td align="left">
                    <input type="text" name="writer" size="10" />
                </td>
            </tr>
            <tr>
                <td bgcolor="orange">내용</td>
                <td align="left">
                    <textarea name="content" cols="40" rows="10"></textarea>
                </td>
            </tr>
            <tr>
                <td bgcolor="orange">파일 등록</td>
                <td align="left">
                    <input type="file" name="uploadFile" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="새글 등록" />
                </td>
            </tr>
        </table>
    </form>

    <hr>

    <a href="/getBoardList.do">글 목록</a>

</body>
</html>
