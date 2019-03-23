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

//    void "test if index loads with correct model"() {
//        given:
//        List<TodoItem> sampleTodoList = [
//                new TodoItem(id: 1, task: 'create test', createdOn: '2019-03-20'),
//                new TodoItem(id: 2, name: 'mock the right things', createdOn: '2019-03-21'),
//                new TodoItem(id: 3, name: 'run the tests', createdOn: '2019-03-22')
//        ]
////        TodoListService todoListService = Mock()
////        controller.todoListService = todoListService
////        controller.todoListService = Stub(TodoListService) {
////            list(TodoItem) >> sampleTodoList
////        }
//
//        when:
//        controller.index()
//        TodoItem getAll = Mock()
//        TodoItem.getAll() {
//            sampleTodoList
//        }
////        TodoItem.getAll(){
////            sampleTodoList
////        }
////        List<TodoItem> list = sampleTodoList
////        list = sampleTodoList
//
//        then:
//        response.status == 200
//        println(' - - - - - model:' + model)
////        model.list
//        list.size() == 3
////        list.find { it.task == 'create test' && it.createdOn == '2019-03-20' }
////        list.find { it.task == 'mock the right things' && it.createdOn == '2019-03-21' }
////        list.find { it.task == 'run the tests' && it.createdOn == '2019-03-22' }
//    }

}
