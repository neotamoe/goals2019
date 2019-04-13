<!doctype html>
<html>
<head>
    <title>Search Results</title>
    <meta name="layout" content="main">
</head>
<body>
<div class="container" style="margin-top: 15px">
    <div>
        <g:link controller="TodoList" action="index">Back to List</g:link>
    </div>
    <h1>To Do List - Completed Tasks</h1>
    <div>
        <g:if test="${completed?.size()>0}">
            <table class="table table-bordered">
                <thead>
                <th>Task</th>
                <th>Delete</th>
                <th>Move to List</th>
                </thead>
                <tbody>
                <g:each var="item" in="${completed}">
                    <tr style="background-color: lightgray">
                        <td style="width: 50%">
                            ${item.task}
                        </td>
                        <td style="width: 25%">
                            <g:link controller="TodoList" action="deleteTask" params="${[taskId: item.id]}" class="red">
                                &#10008;
                            </g:link>
                        </td>
                        <td style="width: 25%">
                            <g:link controller="TodoList" action="moveToList" params="${[taskId: item.id]}">
                                &#11014;
                            </g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <div>No completed tasks--get something done!</div>
        </g:else>
    </div>
</div>
</body>
</html>


