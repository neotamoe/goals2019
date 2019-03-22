package goals2019


class TodoListController {

    List<TodoItem> list
    TodoListService todoListService

    def index() {
        list = TodoItem.getAll()
        render(view: 'todoList', model:[list: list])
    }

    def addTask() {
        todoListService.save(params.newTask)
        list = TodoItem.getAll()
        redirect(view: 'todoList', model:[list: list])
    }

    def deleteTask() {
        list = todoListService.deleteTask(params.taskId)
        redirect(view: 'todoList', model:[list: list])
    }

    def editTask() {
        println('taskId in editTask: ' + params.taskId)
        TodoItem item = TodoItem.findById(params.taskId)
        render(view: 'editTask', model:[item: item])
    }

    def updateTask() {
        todoListService.updateTask(params.id, params.task)
        redirect(view: 'todoList', model: [list: list])
    }
}
