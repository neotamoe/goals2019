package goals2019

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TodoListControllerSpec extends Specification implements ControllerUnitTest<TodoListController>, DataTest {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    def setup() {
        new TodoItem(task: 'create test', createdOn: new Date().format('yyyy-MM-dd')).save()
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    void "add task will call service save"(){
        given:
        params.newTask = "new task to add"
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.addTask()

        then:
        controller.todoListService.save('new task to add')
    }

    void "delete task will call service deleteTask"(){
        given:
        params.taskId = 1
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.deleteTask()

        then:
        controller.todoListService.deleteTask(1)
    }

    void "update task will call service updateTask"(){
        given:
        params.taskId = 1
        params.task = 'new task to add'
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.updateTask()

        then:
        controller.todoListService.updateTask(params.taskId, params.task)
    }

    void "test if index loads"() {
        given:
        List<TodoItem> list = [
                new TodoItem(task: 'create test', createdOn: new Date()),
                new TodoItem(task: 'mock the right things', createdOn: new Date()),
                new TodoItem(task: 'run the tests', createdOn: new Date())
        ]

        when:
        controller.index()


        then:
        response.status == 200
        list.size() == 3

    }

}
