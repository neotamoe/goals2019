package goals2019

import grails.gorm.transactions.Transactional

@Transactional
class TodoListService {

    def save(task) {
        Date today = new Date()
        TodoItem newTask = new TodoItem(task: task, createdOn: today, updatedOn: today)
        newTask.save()
    }

    def deleteTask(id) {
        def task = TodoItem.get(id)
        task.delete(flush: true)
    }

    def updateTask(id, task) {
        Date today = new Date()
        TodoItem taskToUpdate = TodoItem.get(id)
        taskToUpdate.updatedOn = today
        taskToUpdate.task = task
        taskToUpdate.save()
    }

}
