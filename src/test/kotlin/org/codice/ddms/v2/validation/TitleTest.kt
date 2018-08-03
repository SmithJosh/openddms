/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddms.v2.validation

import org.codice.ddms.v2.resource.Title
import org.codice.ddms.v2.security.ism.Classification
import org.codice.ddms.v2.security.ism.SecurityAttributes
import org.junit.Test

class TitleTest {
    @Test(expected = IllegalArgumentException::class)
    fun `a Title with no classification is invalid`() {
        Title(SecurityAttributes(ownerProducer = listOf("")), "Title")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Title with no ownerProducer is invalid`() {
        Title(SecurityAttributes(classification = Classification.U), "Title")
    }

    @Test
    fun `a Title with a classification and ownerProducer is valid`() {
        Title(SecurityAttributes(classification = Classification.U, ownerProducer = listOf("USA")), "Title")
    }
}
