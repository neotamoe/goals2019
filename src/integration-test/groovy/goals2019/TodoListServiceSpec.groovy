package goals2019

import grails.testing.gorm.DataTest
import spock.lang.Specification

class TodoListServiceSpec extends Specification implements DataTest  {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    TodoListService todoListService = new TodoListService()

    def setup() {
    }

    def cleanup() {
    }

    def 'test add task'() {
        when: 'tasks are already in database'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date(), updatedOn: new Date()),
                new TodoItem(task: 'task two', createdOn: new Date(), updatedOn: new Date()),
                new TodoItem(task: 'task three', createdOn: new Date(), updatedOn: new Date())
        )

        then:
        TodoItem.count() == 3

        when: 'service is called to save'
        todoListService.save('some new task')
        TodoItem taskAdded = TodoItem.findById(4)

        then:
        TodoItem.count() == 4
        taskAdded.task == 'some new task'
    }

    def 'test update task'() {
        when: 'tasks are already in database'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date(), updatedOn: new Date()),
                new TodoItem(task: 'task two', createdOn: new Date(), updatedOn: new Date()),
                new TodoItem(task: 'task three', createdOn: new Date(), updatedOn: new Date())
        )

        then:
        TodoItem.count() == 3
        TodoItem taskAtHand = TodoItem.findById(3)
        taskAtHand.task == 'task three'
        taskAtHand.createdOn == taskAtHand.updatedOn

        when: 'service is called to update task'
        todoListService.updateTask(3,'some new task')

        then:
        TodoItem.count() == 3
        taskAtHand.task == 'some new task'
        taskAtHand.task != 'task three'
        taskAtHand.createdOn != taskAtHand.updatedOn
        taskAtHand.updatedOn.format('yyyy-MM-dd') == new Date().format('yyyy-MM-dd')
    }

    def 'test delete task'() {
        when: 'tasks are already in database'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date()),
                new TodoItem(task: 'task two', createdOn: new Date()),
                new TodoItem(task: 'task three', createdOn: new Date())
        )

        then:
        TodoItem.count() == 3

        when: 'service is called to delete task'
        todoListService.deleteTask(2)
        TodoItem taskDeleted = TodoItem.findById(2)

        then:
        TodoItem.count() == 2
        taskDeleted == null
    }


}
