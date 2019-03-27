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

    def 'test complete task'() {
        when: 'tasks are already in db'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task two', createdOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task three', createdOn: new Date(), isCompleted: false)
        )

        then:
        TodoItem.count() == 3
        TodoItem taskCompleted = TodoItem.findById(2)
        taskCompleted.isCompleted == false

        when: 'service is called to complete task'
        todoListService.completeTask(2)

        then:
        taskCompleted.isCompleted == true
    }

    def 'test getAll'() {
        when: 'tasks are already in db'
        TodoItem.saveAll(
                new TodoItem(task: 'task one', createdOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task two', createdOn: new Date(), isCompleted: false),
                new TodoItem(task: 'task three', createdOn: new Date(), isCompleted: false)
        )

        then:
        TodoItem.count() == 3

        when: 'getAll is called'
        List<TodoItem>  list = TodoItem.getAll()

        then:
        list.size() == 3
        list.get(0).task == 'task one'
        list.get(1).task == 'task two'
        list.get(2).task == 'task three'
    }

}
