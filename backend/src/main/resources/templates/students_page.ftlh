<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    <#list students as student>
        <tr>
            <td>${students.id}</td>
            <td>${students.firstName}</td>
            <td>${students.lastName}</td>
            <td>${students.email}</td>
        </tr>
    </#list>
</table>
</body>
</html>