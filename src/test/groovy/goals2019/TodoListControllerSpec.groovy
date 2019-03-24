package goals2019

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TodoListControllerSpec extends Specification implements ControllerUnitTest<TodoListController>, DataTest {

    Class<TodoItem>[] getDomainClassesToMock(){
        return [TodoItem] as Class[]
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    void "add task will call controller"(){
        given:
        params.newTask = "new task to add"
        controller.todoListService = Stub(TodoListService) {
        }

        when:
        controller.addTask()

        then:
        controller.todoListService.save('new task to add')
    }

    void "test if index loads"() {
        given:
        List<TodoItem> list = [
                new TodoItem(task: 'create test', createdOn: '2019-03-20'),
                new TodoItem(task: 'mock the right things', createdOn: '2019-03-21'),
                new TodoItem(task: 'run the tests', createdOn: '2019-03-22')
        ]

        when:
        controller.index()


        then:
        response.status == 200
        list.size() == 3

    }

}
