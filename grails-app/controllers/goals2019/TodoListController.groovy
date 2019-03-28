package goals2019


class TodoListController {

    List<TodoItem> list
    List<TodoItem> completed
    TodoListService todoListService

    def index() {
        list = TodoItem.findAllByIsCompleted(false)
        completed = TodoItem.findAllByIsCompleted(true)
        render(view: 'todoList', model:[list: list, completed: completed])
    }

    def addTask() {
        todoListService.save(params.newTask)
        list = TodoItem.getAll()
        redirect(view: 'todoList', model:[list: list])
    }

    def deleteTask() {
        todoListService.deleteTask(params.taskId)
        list = TodoItem.getAll()
        redirect(view: 'todoList', model:[list: list])
    }

    def editTask() {
        TodoItem item = TodoItem.findById(params.taskId)
        render(view: 'editTask', model:[item: item])
    }

    def updateTask() {
        todoListService.updateTask(params.id, params.task)
        list = TodoItem.getAll()
        redirect(view: 'todoList', model: [list: list])
    }

    def completeTask() {
        todoListService.completeTask(params.taskId)
        list = TodoItem.getAll()
        redirect(view: 'todoList', model: [list: list])
    }
}
