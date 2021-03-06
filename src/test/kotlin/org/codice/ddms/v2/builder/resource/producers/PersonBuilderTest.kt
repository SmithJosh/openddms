/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.v2.builder.resource.producers

import org.codice.ddms.v2.resource.producers.Person
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PersonBuilderTest {
    private val person = Person(listOf("name"),
            "surname",
            "userId",
            "affiliation",
            listOf("phones"),
            listOf("emails"))

    @Test
    fun `building with methods`() {
        val result = PersonBuilder()
                .names(person.names)
                .surname(person.surname)
                .userId(person.userId)
                .affiliation(person.affiliation)
                .phones(person.phones)
                .emails(person.emails)
                .build()

        assertThat(result, equalTo(person))
    }

    @Test
    fun `building with lambda`() {
        val result = PersonBuilder.person {
            names(person.names)
            surname(person.surname)
            userId(person.userId)
            affiliation(person.affiliation)
            phones(person.phones)
            emails(person.emails)
        }

        assertThat(result, equalTo(person))
    }
}
