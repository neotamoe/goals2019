package goals2019


class TodoListController {

    TodoListService todoListService

    def index() {
        log.debug("to do list index")
        List<TodoItem> list = TodoItem.findAllByIsCompleted(false)
        List<TodoItem> completed  = TodoItem.findAllByIsCompleted(true)
        render(view: 'todoList', model:[list: list, completed: completed])
    }

    def addTask(TodoItem requestItem) {
        log.debug("adding task: " + requestItem.task)
        def persistedItem = todoListService.addTask(requestItem)
        if(!persistedItem){
            log.error("Error in add task--task ${requestItem.task} was not saved")
            forward(view: 'todoList', model: [item: requestItem])
        } else {
            forward(view: 'todoList', model:[item: persistedItem])
        }
    }

    def deleteTask() {
        log.debug("deleting task: " + params.taskId)
        todoListService.deleteTask(params.taskId)
        redirect(view: 'todoList')
    }

    def editTask() {
        log.debug("editing task: " + params.taskId)
        TodoItem item = TodoItem.findById(params.taskId)
        render(view: 'editTask', model:[item: item])
    }

    def updateTask() {
        log.debug("updating task: " + params.id + " with new task: " + params.task)
        todoListService.updateTask(params.id, params.task)
        redirect(view: 'todoList')
    }

    def completeTask() {
        log.debug("completing task: " + params.taskId)
        todoListService.completeTask(params.taskId)
        redirect(view: 'todoList')
    }

    def moveToList() {
        log.debug("moving task " + params.taskId + " back to list")
        todoListService.moveToList(params.taskId)
        redirect(view: 'todoList')
    }

    def searchTasks() {
        log.debug("searching for task: " + params.search)
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
