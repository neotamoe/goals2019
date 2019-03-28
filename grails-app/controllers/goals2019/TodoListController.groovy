package goals2019


class TodoListController {

    TodoListService todoListService

    def index() {
        List<TodoItem> list = TodoItem.findAllByIsCompleted(false)
        List<TodoItem> completed = TodoItem.findAllByIsCompleted(true)
        render(view: 'todoList', model:[list: list, completed: completed])
    }

    def addTask() {
        todoListService.save(params.newTask)
        redirect(view: 'todoList')
    }

    def deleteTask() {
        todoListService.deleteTask(params.taskId)
        redirect(view: 'todoList')
    }

    def editTask() {
        TodoItem item = TodoItem.findById(params.taskId)
        render(view: 'editTask', model:[item: item])
    }

    def updateTask() {
        todoListService.updateTask(params.id, params.task)
        redirect(view: 'todoList')
    }

    def completeTask() {
        todoListService.completeTask(params.taskId)
        redirect(view: 'todoList')
    }
}
