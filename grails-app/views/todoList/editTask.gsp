<!doctype html>
<html>
    <head>
        <title>Edit Task</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container" style="margin-top: 15px">
            <form class="form-inline" controller="ToDoList" action="updateTask" params="[id: id, task: task]" autocomplete="off">
                <div>
                    <label for="task">Edit Task</label>
                    <input type="text" value="${item.task}" name="task" id="task"/>
                    <input type="hidden" value="${item.id}" name="id" />
                    <button type="submit" class="green">&#10004;</button>
                    <button type="cancel">Cancel</button>
                </div>
            </form>
        </div>
    </body>
</html>


