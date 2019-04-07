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
        def updatedItem = todoListService.updateTask(params.id, params.task)
        if(!updatedItem){
            log.error("Error in update task--id: ${params.id} NOT updated with new task ${params.task}")
            forward(view: 'todoList', model: [item: updatedItem])
        } else {
            redirect(view: 'todoList')
        }
    }

    def completeTask() {
        log.debug("completing task: " + params.taskId)
        def completedTask = todoListService.completeTask(params.taskId)
        if(!completedTask){
            log.error("Error in complete task--id: ${params.taskId} not completed")
            forward(view: 'todoList', model: [item: completedTask])
        } else {
            redirect(view: 'todoList')
        }
    }

    def moveToList() {
        log.debug("moving task " + params.taskId + " back to list")
        def movedItem = todoListService.moveToList(params.taskId)
        if(!movedItem){
            log.error("Error in move to list--task id: ${params.taskId} not moved")
            forward(view: 'todoList', model: [item: movedItem])
        } else {
            redirect(view: 'todoList')
        }
    }

    def searchTasks() {
        log.debug("searching for task: " + params.search)
        List<TodoItem> list = new ArrayList<TodoItem>()
        List<TodoItem> completed = new ArrayList<TodoItem>()
        List<TodoItem> results = todoListService.searchTasks(params.search)
        if(!results){
            log.error("Error in search tasks for ${params.search}")
            forward(view: 'todoList', model: [item: results])
        } else {
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
}
