<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>File Upload and Download</title>
    <style>
        table { border-collapse: collapse; width: 50%; margin: 20px auto; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
    </style>
</head>
<body>
    <h1 style="text-align:center;">File Upload and Download</h1>

    <form method="post" action="/upload" enctype="multipart/form-data" style="text-align:center;">
        <input type="file" name="file" required>
        <button type="submit">Upload</button>
    </form>

    <p style="text-align:center;">${message}</p>

    <h2 style="text-align:center;">Uploaded Files</h2>
    <table>
        <tr>
            <th>File Name</th>
            <th>Download</th>
        </tr>
        <c:forEach items="${files}" var="file">
            <tr>
                <td>${file.fileName}</td>
                <td><a href="/download${file.id}/">Download</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>