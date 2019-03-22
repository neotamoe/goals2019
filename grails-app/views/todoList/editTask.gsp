<!doctype html>
<html>
    <head>
        <title>Edit Task</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container">
            <form controller="ToDoList" action="updateTask" params="[id: id, task: task]">
                <input type="text" value="${item.task}" name="task"/>
                <input type="hidden" value="${item.id}" name="id"/>
                <button type="submit" class="green">&#10004;</button>
            </form>
        </div>
    </body>
</html>


