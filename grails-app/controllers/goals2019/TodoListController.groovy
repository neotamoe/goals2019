package goals2019


class TodoListController {

    TodoListService todoListService

    def index() {
        List<TodoItem> list = null
        List<TodoItem> completed = null
        try {
            list = TodoItem.findAllByIsCompleted(false)
            completed = TodoItem.findAllByIsCompleted(true)
        } catch(Exception ex) {
            log.error("Error in getting lists for index ${ex.message}", ex)
        }
        render(view: 'todoList', model:[list: list, completed: completed])
    }

    def addTask() {
        log.debug("adding task: " + params.newTask)
        try {
            todoListService.save(params.newTask)
        } catch(Exception ex) {
            log.error("Error in adding task ${ex.message}", ex)
        }
        redirect(view: 'todoList')
    }

    def deleteTask() {
        log.debug("deleting task: " + params.taskId)
        try {
            todoListService.deleteTask(params.taskId)
        } catch(Exception ex) {
            log.error("Error in deleting task ${ex.message}", ex)
        }
        redirect(view: 'todoList')
    }

    def editTask() {
        try {
            TodoItem item = TodoItem.findById(params.taskId)
            render(view: 'editTask', model:[item: item])
        } catch(Exception ex) {
            log.error("Error in edit task ${ex.message}", ex)
        }
    }

    def updateTask() {
        try {
            todoListService.updateTask(params.id, params.task)
            redirect(view: 'todoList')
        } catch(Exception ex) {
            log.error("error in update task ${ex.message}", ex)
        }
    }

    def completeTask() {
        try {
            todoListService.completeTask(params.taskId)
            redirect(view: 'todoList')
        } catch(Exception ex) {
            log.error("error in complete task ${ex.message}", ex)
        }
    }

    def moveToList() {
        try {
            todoListService.moveToList(params.taskId)
            redirect(view: 'todoList')
        } catch(Exception ex) {
            log.error("error in move to list ${ex.message}", ex)
        }
    }

    def searchTasks() {
        try {
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
        } catch(Exception ex) {
            log.error("error in search tasks ${ex.message}", ex)
        }
    }
}
