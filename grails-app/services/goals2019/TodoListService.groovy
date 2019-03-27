package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    // TODO: Use java.time instead of java.util.Date - will need to change in service and controller
    def save(task) {
        Date today = new Date()
        TodoItem newTask = new TodoItem(task: task, createdOn: today, updatedOn: today, isCompleted: false)
        newTask.save()
    }

    def deleteTask(id) {
        def task = TodoItem.get(id)
        task.delete(flush: true)
    }

    def updateTask(id, task) {
        Date today = new Date()
        TodoItem taskToUpdate = TodoItem.findById(id)
        taskToUpdate.updatedOn = today
        taskToUpdate.task = task
        taskToUpdate.save()
    }

    def completeTask(id) {
        TodoItem completedTask = TodoItem.findById(id)
        completedTask.updatedOn = new Date()
        completedTask.isCompleted = true
        completedTask.save()
    }
}
