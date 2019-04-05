package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    // TODO: Use java.time instead of java.util.Date - will need to change in service and controller
    def addTask(requestItem) {
        Date today = new Date()
        TodoItem itemToAdd = new TodoItem(task: requestItem.task, createdOn: today, updatedOn: today, isCompleted: false)
        itemToAdd.save(flush: true)
    }

    def deleteTask(id) {
        def task = TodoItem.get(id)
        task.delete(flush: true)
    }

    def updateTask(id, task) {
        TodoItem taskToUpdate = TodoItem.findById(id)
        taskToUpdate.updatedOn = new Date()
        taskToUpdate.task = task
        taskToUpdate.save(flush: true)
    }

    def completeTask(id) {
        TodoItem completedTask = TodoItem.findById(id)
        completedTask.updatedOn = new Date()
        completedTask.isCompleted = true
        completedTask.save(flush: true)
    }

    def moveToList(id) {
        TodoItem taskToMove = TodoItem.findById(id)
        taskToMove.updatedOn = new Date()
        taskToMove.isCompleted = false
        taskToMove.save(flush: true)
    }

    def searchTasks(search) {
        String searchLike = "%" + search + "%".toLowerCase()
        if (searchLike.contains(" ")) {
            searchLike = searchLike.replaceAll("[,\\s;\\-()/]","%")
        }
        List<TodoItem> results = TodoItem.findAllByTaskIlike(searchLike)
    }
}
