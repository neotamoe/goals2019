<!doctype html>
<html>
    <head>
        <title>To Do List</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container">
            <h1>To Do List</h1>
            <g:form name="addTask" controller="TodoList" action="addTask" method="POST" autocomplete="off">
                <input type="text" placeholder="add task" name="newTask"/>
                <button type="submit">Add Task</button>
            </g:form>
            <g:if test="${list.size()>0}">
                <table class="table table-bordered">
                    <thead>
                    <th>Task</th>
                    <th>Delete</th>
                    <th>Edit</th>
                    </thead>
                    <tbody>
                    <g:each var="item" in="${list}">
                        <tr>
                            <td>
                                ${item.task}
                            </td>
                            <td>
                                <g:link controller="TodoList" action="editTask" params="${[taskId: item.id]}">
                                    &#10000;
                                </g:link>
                            </td>
                            <td>
                                <g:link controller="TodoList" action="deleteTask" params="${[taskId: item.id]}">
                                    &#10004;
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <div>No items in your to do list!</div>
            </g:else>
        </div>
    </body>
</html>


