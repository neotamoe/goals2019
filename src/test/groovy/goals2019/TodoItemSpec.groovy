package goals2019

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TodoItemSpec extends Specification implements DomainUnitTest<TodoItem> {

    def setup() {
    }

    def cleanup() {
    }

    void 'test task cannot be null'() {
        when:
        domain.task = null

        then:
        !domain.validate(['task'])
        domain.errors['task'].code == 'nullable'
    }

    void 'test task cannot be blank'() {
        when:
        domain.task = ''

        then:
        !domain.validate(['task'])
    }

    void 'test task can have a maximum of 255 characters'() {
        when: 'for a string of 256 characters'
        String str = 'a' * 256
        domain.task = str

        then: 'task validation fails'
        !domain.validate(['task'])
        domain.errors['task'].code == 'maxSize.exceeded'

        when: 'for a string of 256 characters'
        str = 'a' * 255
        domain.task = str

        then: 'task validation passes'
        domain.validate(['task'])
    }
}
