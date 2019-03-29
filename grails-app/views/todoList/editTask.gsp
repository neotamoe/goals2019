<!doctype html>
<html>
    <head>
        <title>Edit Task</title>
        <meta name="layout" content="main">
    </head>
    <body>
        <div class="container" style="margin-top: 15px">
            <h1>Edit Task</h1>
            <form class="form-inline" controller="ToDoList" action="updateTask" params="[id: id, task: task]" autocomplete="off">
                <div>
                    <input type="text" value="${item.task}" name="task" id="task" style="width: 33%"/>
                    <input type="hidden" value="${item.id}" name="id" />
                    <button type="submit" class="green">Save Changes</button>
                    <button type="cancel">Cancel</button>
                </div>
            </form>
        </div>
    </body>
</html>


