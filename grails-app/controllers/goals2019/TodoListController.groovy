package goals2019


class TodoListController {

    TodoListService todoListService

    def index() {
        List<TodoItem> list = TodoItem.findAllByIsCompleted(false)
        List<TodoItem> completed = TodoItem.findAllByIsCompleted(true)
        render(view: 'todoList', model:[list: list, completed: completed])
    }

    def addTask() {
        log.debug("adding task: " + params.newTask)
        todoListService.save(params.newTask)
        redirect(view: 'todoList')
    }

    def deleteTask() {
        log.debug("deleting task: " + params.taskId)
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

    def moveToList() {
        todoListService.moveToList(params.taskId)
        redirect(view: 'todoList')
    }

    def searchTasks() {
        List<TodoItem> list = new ArrayList<TodoItem>()
        List<TodoItem> completed = new ArrayList<TodoItem>()
        List<TodoItem> results = todoListService.searchTasks(params.search)
        results?.each {
            if(it.isCompleted){
                completed.add(it)
            } else {
                list.add(it)
            }
        }
         render(view: 'searchResults', model:[list: list, completed: completed, search: params.search])
    }
}
